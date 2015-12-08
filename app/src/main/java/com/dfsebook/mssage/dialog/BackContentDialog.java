package com.dfsebook.mssage.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.DialogProperty;
import com.dfsebook.mssage.util.DataSource;

import java.io.Serializable;

/**
 * Created by Administrator on 15-11-9.
 */
public class BackContentDialog extends DialogFragment {

    private DialogProperty dialogProperty;

    private EditText editText;

    public static BackContentDialog newInstance(DialogProperty dialogProperty){
        BackContentDialog backContentDialog = new BackContentDialog();
        Bundle args = new Bundle();
        args.putSerializable("dialogProperty",dialogProperty);
        backContentDialog.setArguments(args);
        return backContentDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        if(getArguments() != null)
            this.dialogProperty = (DialogProperty)getArguments().getSerializable("dialogProperty");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        editText = (EditText)view.findViewById(R.id.content_edit);
        editText.setHint(dialogProperty.getHintText());
        builder.setView(view)
                .setPositiveButton(dialogProperty.getPositiveButtonText(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult();
                    }
                })
                .setNegativeButton(dialogProperty.getNegativeButtonText(), null);
        return builder.create();
    }

    private void setResult(){
        if (TextUtils.isEmpty(editText.getText())){
            Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String content = editText.getText().toString();
        content = DataSource.transURLEncoderString(content);
        if (getTargetFragment() == null) {
            OnBackContentListener listener = (OnBackContentListener) getActivity();
            listener.onBackContentComplete(content);
        }
        else {
            Intent intent = new Intent();
            intent.putExtra("content", content);
            getTargetFragment().onActivityResult(dialogProperty.getRequestype(), Activity.RESULT_OK, intent);
        }
    }

    public interface OnBackContentListener{
        void onBackContentComplete(String content);
    }
}
