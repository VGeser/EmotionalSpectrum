package group_project.main.emotionalspectrum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import group_project.main.emotionalspectrum.calc.Calculator;
import group_project.main.emotionalspectrum.fragments.AdviceFragment;
import group_project.main.emotionalspectrum.fragments.ConfirmationFragment;

public class MainActivity extends FragmentActivity {
    private ImageView image;
    private TextView textView;
    private String current = "";
    private int currentID;
    private GsonEditor gsonEditor;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        //set up clickable image and latest text
        this.image = this.findViewById(R.id.spectrum1);
        this.textView = this.findViewById(R.id.text);
        this.image.setImageResource(R.drawable.round_without_emots);
        ConfirmationFragment cf = new ConfirmationFragment();
        //set up Shared Prefs
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gsonEditor = GsonEditor.getInstance(sharedPreferences.getString("data", ""));

        image.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();
                //if less than 5 a day we allow add new record
                if (checkClicks()) {
                    Calculator calculator = Calculator.getInstance();
                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();
                    int width = getWindowManager().getCurrentWindowMetrics().getBounds().width();
                    cf.currentRecord = calculator.calculate(curX, curY, (float) (width / 2.0));

                    if (!cf.currentRecord.getEmotionName().equals("out_of_circle"))
                        cf.show(getSupportFragmentManager(), "confirm");
                } else { //if more than 5 we forbid and notify user
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

    private void setMostRecent() {
        if (sharedPreferences.contains("most_recent")) {
            current = sharedPreferences.getString("most_recent", "");
            textView.setText(current);
        } else {
            editor.putString("most_recent", current);
        }
    }

    private void setCurId() {
        if (sharedPreferences.contains("current_id")) {
            currentID = sharedPreferences.getInt("cur_id", 0);
        } else {
            editor.putInt("cur_id", currentID);
        }
    }

    private void setName() {
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
    }

    private void setNextId() {
        if (sharedPreferences.contains("next_id")) {
            this.currentID = sharedPreferences.getInt("next_id", 0);
        } else {
            currentID = 0;
            editor.putInt("next_id", 0);
        }
    }

    private String getData() {
        if (!sharedPreferences.contains("data")) {
            editor.putString("data", "");
        }
        editor.apply();
        return sharedPreferences.getString("data", "");
    }

    private void setFreqs() {
        if (!sharedPreferences.contains("freqs")) {
            editor.putString("freqs", "");
        } else {
            String loadedFreqs = sharedPreferences.getString("freqs", "");
            gsonEditor.loadFreq(loadedFreqs);
        }
    }

    private void setIntens() {
        if (!sharedPreferences.contains("intens")) {
            editor.putString("intens", "");
        } else {
            String loadedIntens = sharedPreferences.getString("intens", "");
            gsonEditor.loadIntens(loadedIntens);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        gsonEditor = GsonEditor.getInstance();
        setMostRecent();
        setCurId();
        setName();
        setNextId();
        gsonEditor.parseGson(getData());
        setFreqs();
        setIntens();
        editor.apply();
    }

    public void doPositiveClick(Record next) {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String dateS = date.toString();
        next.setDate(dateS);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String formatted = time.format(dtf);
        current = ("You were feeling " + next.getEmotionName() + " at " + formatted + "\n");
        textView.append(current);
        gsonEditor.addRecord(currentID, next);
        currentID++;
        displayAdvice();
    }

    public void doNegativeClick() {
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

    private void saveData() {
        String savedAs = gsonEditor.saveData();
        if (!savedAs.equals("{}")) {
            editor.putString("data", savedAs);
        }
    }

    private void saveFreq() {
        String savedFreq = gsonEditor.saveFreq();
        if (!savedFreq.equals("{}")) {
            editor.putString("freqs", savedFreq);
        }
    }

    private void saveIntens() {
        String savedIntens = gsonEditor.saveIntens();
        if (!savedIntens.equals("{}")) {
            editor.putString("intens", savedIntens);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putInt("next_id", currentID);
        saveData();
        saveFreq();
        saveIntens();
        editor.putString("most_recent", current);
        editor.putInt("cur_id", currentID);
        editor.apply();
    }

}