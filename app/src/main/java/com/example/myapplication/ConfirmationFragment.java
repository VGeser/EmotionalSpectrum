package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ConfirmationFragment extends DialogFragment implements CurrentValue{
    private String out_text;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //((MainActivity) getActivity()).setCurrentValue(ConfirmationFragment.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void setCurrentMood(byte id) {
        switch (id){
            case 0:
                out_text = "For case 0";
            case 1:
                out_text = "For case 1";
            default:
                out_text = "For default";
        }
    }
}
