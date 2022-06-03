package com.devpj.foodiestorev2.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.devpj.foodiestorev2.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CustomAlert implements View.OnClickListener{

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog dialog;
    ImageView cancel_button;
    TextView accept,reject;
    onAcceptClickListener onAcceptClickListener;



    public void show_alert(Context context,onAcceptClickListener onAcceptClickListener){
        try{
            View alertCustomdialog = LayoutInflater.from(context).inflate(R.layout.customalert,null);
          alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setView(alertCustomdialog);
           cancel_button = (ImageView)alertCustomdialog.findViewById(R.id.cancel_button);
           accept = alertCustomdialog.findViewById(R.id.txtaccept);
           reject = alertCustomdialog.findViewById(R.id.txt_cancel);
           accept.setOnClickListener(this::onClick);
           this.onAcceptClickListener=onAcceptClickListener;
           reject.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   dialog.dismiss();
               }
           });

           cancel_button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   dialog.dismiss();
               }
           });
           dialog = alertDialogBuilder.create();
          //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //finally show the dialog box in android all
            dialog.show();

        }catch (Exception ex){
            Log.d("Error",ex.toString()+"");
        }
    }


    public void dismiss_alert(Context context){
        try{
            if (dialog != null) {
                try {
                    dialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception ex){
            Log.d("Error", ex.toString()+"");
        }
    }

    @Override
    public void onClick(View view) {
       onAcceptClickListener.onAcceptClick("Clicked");


    }


    public interface onAcceptClickListener{

        void onAcceptClick(String orderid);

    }




}
