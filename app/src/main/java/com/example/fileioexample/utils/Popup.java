package com.example.fileioexample.utils;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fileioexample.R;

public class Popup {



    public static void createNewAlertPopup(String message, AppCompatActivity activity){

        //Setup the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        final View alertPopupView = activity.getLayoutInflater().inflate(R.layout.alert_popup, null);

        TextView alertMessageTextView = (TextView) alertPopupView.findViewById(R.id.textView13);
        Button alertCancelButton = (Button) alertPopupView.findViewById(R.id.button2);

        alertMessageTextView.setText(message);

        //Display the alert dialog
        dialogBuilder.setView(alertPopupView);
        AlertDialog dialog = dialogBuilder.create();

        dialog.show();

        //Setup the button functionality to dismiss the dialog
        alertCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
