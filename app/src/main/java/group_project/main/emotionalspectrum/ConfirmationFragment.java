package group_project.main.emotionalspectrum;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ConfirmationFragment extends DialogFragment {
    Record currentRecord;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You chose " + currentRecord.getEmotionName());
        builder.setPositiveButton(R.string.agree, (dialog, id) -> ((MainActivity) getActivity()).doPositiveClick(currentRecord));
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> ((MainActivity) getActivity()).doNegativeClick());

        builder.create();
        return builder.create();
    }

}
