package group_project.main.emotionalspectrum;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    private ImageView image;
    private TextView textView;
    private String current = "";
    private int currentID;
    private GsonEditor gsonEditor;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        this.image = this.findViewById(R.id.spectrum1);
        this.textView = this.findViewById(R.id.text);
        this.image.setImageResource(R.drawable.round_without_emots);
        ConfirmationFragment cf = new ConfirmationFragment();
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gsonEditor = GsonEditor.getInstance(sharedPreferences.getString("data", ""));

        image.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();
                if (checkClicks()) {
                    Calculator calculator = Calculator.getInstance();
                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();
                    int width = getWindowManager().getCurrentWindowMetrics().getBounds().width();
                    cf.currentRecord = calculator.calculate(curX, curY, (float) (width / 2.0));

                    if (!cf.currentRecord.getEmotionName().equals("out_of_circle"))
                        cf.show(getSupportFragmentManager(), "confirm");
                } else {
                    Snackbar snackbar = Snackbar.make(view, getResources().
                            getString(R.string.warning), Snackbar.LENGTH_INDEFINITE).
                            setAction(getResources().getString(R.string.cancel),
                                    view1 -> {
                                    });
                    snackbar.show();
                }

            }
            return view.onTouchEvent(motionEvent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPreferences.contains("most_recent")) {
            current = sharedPreferences.getString("most_recent", "");
            Log.println(Log.ERROR, "Reached", "has most recent");
            textView.setText(current);
        } else {
            editor.putString("most_recent", current);
            Log.println(Log.ERROR, "Reached", "no recent");
        }
        if (sharedPreferences.contains("current_id")) {
            currentID = sharedPreferences.getInt("cur_id", 0);
            Log.println(Log.ERROR, "Reached", "has id");
        } else {
            editor.putInt("cur_id", currentID);
        }
        if (sharedPreferences.contains("name_setting")) {
            boolean nameOn = sharedPreferences.getBoolean("name_setting", false);
            if (nameOn) {
                image.setImageResource(R.drawable.round);
            } else {
                image.setImageResource(R.drawable.round_without_emots);
            }
        } else {
            editor.putBoolean("name_setting", false);
        }
        if (sharedPreferences.contains("next_id")) {
            this.currentID = sharedPreferences.getInt("next_id", 0);
        } else {
            currentID = 0;
            editor.putInt("next_id", 0);
        }
        String json;
        if (!sharedPreferences.contains("data")) {
            editor.putString("data", "");
        }
        editor.apply();
        json = sharedPreferences.getString("data", "");
        Log.println(Log.ERROR, "Current MA data", json);
        Log.println(Log.ERROR, "Current MA ID", String.valueOf(currentID));
        Log.println(Log.ERROR, "Current MA str", current);
        gsonEditor = GsonEditor.getInstance();
        gsonEditor.parseGson(json);
    }

    void doPositiveClick(Record next) {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String dateS = date.toString();
        next.setDate(dateS);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String formatted = time.format(dtf);
        current = ("You were feeling " + next.getEmotionName() + " at " + formatted + "\n");
        textView.append(current);
        gsonEditor.addRecord(currentID, next);
        Log.println(Log.ERROR,"Last Record:",next.getEmotionName()+"\n");
        currentID++;
        displayAdvice();
    }

    void doNegativeClick() {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void statsClick(View view) {
        Intent i = new Intent(MainActivity.this, StatsActivity.class);
        MainActivity.this.startActivity(i);
    }

    public void settingsClick(View view) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(i);
    }

    private boolean checkClicks() {
        Map<Integer, Record> rawData = gsonEditor.flatten();
        String border;
        if (rawData.size() >= 5) {
            Record rec = gsonEditor.getRecord(currentID - 5);
            assert rec != null;
            Log.println(Log.ERROR, "Current record", String.valueOf(rec.getID()));
            border = rec.getDate();
            LocalDate d2 = LocalDate.now();
            String now = d2.toString();
            return !border.equals(now);
        }
        return true;
    }

    private void displayAdvice() {
        AdviceFragment af = new AdviceFragment();
        int cuid;
        if (currentID == 0) {
            cuid = 0;
        } else {
            cuid = currentID - 1;
        }
        af.currentRecord = gsonEditor.getRecord(cuid);
        if (af.currentRecord != null && af.currentRecord.getIntensity() >= 5) {
            af.show(getSupportFragmentManager(), "advice");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putInt("next_id", currentID);
        Log.println(Log.ERROR, "Putting", String.valueOf(currentID));
        String savedAs = gsonEditor.saveData();
        Log.println(Log.ERROR,"Editors' data",gsonEditor.flatten().toString());
        if (!savedAs.equals("{}")) {
            editor.putString("data", savedAs);
            Log.println(Log.ERROR, "Putting", savedAs);
        }
        editor.putString("most_recent", current);
        Log.println(Log.ERROR, "Putting", current);
        editor.putInt("cur_id", currentID);
        editor.apply();
        Log.println(Log.ERROR, "Action", "Saved");
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Now you can delete");
        toast.show();
    }


    public void ClickMenu (View view) {
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo (View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome (View view){
        recreate();
    }

    public void ClickSettings (View view){
        redirectActivity(this, SettingsActivity.class);
    }

    public void ClickStatistics (View view){
        redirectActivity(this, StatsActivity.class);
    }

    public void ClickGetHelp (View view){
        redirectActivity(this, GetHelpActivity.class);
    }

    public void ClickContactUs (View view){
        redirectActivity(this, ContactUsActivity.class);
    }

    public void ClickExit (View view){
        exit(this);
    }

    private static void exit(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}