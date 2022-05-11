package group_project.main.emotionalspectrum;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AdviceFragment extends DialogFragment {
    Record currentRecord;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String currentEmotion = currentRecord.getEmotionName();
        Resources res = getResources();
        String[] guiltAdvices = res.getStringArray(R.array.guiltAdvices);
        String[] fearAdvices = res.getStringArray(R.array.fearAdvices);
        String[] angerAdvices = res.getStringArray(R.array.angerAdvices);
        String[] shameAdvices = res.getStringArray(R.array.shameAdvices);
        String[] sufferingAdvices = res.getStringArray(R.array.sufferingAdvices);
        String[] disguiseAdvices = res.getStringArray(R.array.disguiseAdvices);
        String[] contemptAdvices = res.getStringArray(R.array.contemptAdvices);
        String[] anticipationAdvices = res.getStringArray(R.array.anticipationAdvices);
        String[] prideAdvices = res.getStringArray(R.array.prideAdvices);
        String[] joyAdvices = res.getStringArray(R.array.joyAdvices);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        switch(currentEmotion) {
            case "guilt":
                builder.setMessage(guiltAdvices[(int)Math.floor(Math.random() * guiltAdvices.length)]);
                break;
            case "fear":
                builder.setMessage(fearAdvices[(int)Math.floor(Math.random() * fearAdvices.length)]);
                break;
            case "anger":
                builder.setMessage(angerAdvices[(int)Math.floor(Math.random() * angerAdvices.length)]);
                break;
            case "shame":
                builder.setMessage(shameAdvices[(int)Math.floor(Math.random() * shameAdvices.length)]);
                break;
            case "suffering":
                builder.setMessage(sufferingAdvices[(int)Math.floor(Math.random() * sufferingAdvices.length)]);
                break;
            case "disguise":
                builder.setMessage(disguiseAdvices[(int)Math.floor(Math.random() * disguiseAdvices.length)]);
                break;
            case "contempt":
                builder.setMessage(contemptAdvices[(int)Math.floor(Math.random() * contemptAdvices.length)]);
                break;
            case "anticipation":
                builder.setMessage(anticipationAdvices[(int)Math.floor(Math.random() * anticipationAdvices.length)]);
                break;
            case "pride":
                builder.setMessage(prideAdvices[(int)Math.floor(Math.random() * prideAdvices.length)]);
                break;
            case "joy":
                builder.setMessage(joyAdvices[(int)Math.floor(Math.random() * joyAdvices.length)]);
                break;
        }

        builder.setPositiveButton(R.string.agree, (dialog, id) -> {
        });

        builder.create();
        return builder.create();
    }
}
