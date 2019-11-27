package com.smith.temp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.smith.temp.R;

public class MyDialogFragment extends DialogFragment {
    private static final String TITLE = "title";
    private static boolean withOnCreateDialog = true;
    Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static MyDialogFragment newInstance(String title, boolean withDialog) {
        MyDialogFragment.withOnCreateDialog = withDialog;
        MyDialogFragment dialogFragment = new MyDialogFragment();
        Bundle bun = new Bundle();
        bun.putString(TITLE, title);
        dialogFragment.setArguments(bun);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (withOnCreateDialog) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity)
                    .setTitle(this.getArguments().getString(TITLE))
                    .setMessage("请继续")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .create();

            Window window = alertDialog.getWindow();
            window.setBackgroundDrawableResource(android.R.color.white);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
            return alertDialog;
        }
        return super.onCreateDialog(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (withOnCreateDialog) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = LayoutInflater.from(activity).inflate(R.layout.temp_dialog_layout, container, false);
            return view;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!withOnCreateDialog) {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawableResource(android.R.color.white);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }

    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }
}
