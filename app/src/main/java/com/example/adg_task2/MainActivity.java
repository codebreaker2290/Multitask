package com.example.adg_task2;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallPulseIndicator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final Context context=this;

    Button progress;
    AVLoadingIndicatorView progressbar;
    CountDownTimer ct;
    private static final String TAG = "MainActivity";

    private CoordinatorLayout layout;
    private Button snack;

    //TOAST
    public void displayToast(View v){
        Toast.makeText(MainActivity.this,"TOAST MAKES ME HUNGRY!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PROGRESS BAR
        progress=findViewById(R.id.progressbar);
        progressbar=findViewById(R.id.avl);
        final AVLoadingIndicatorView spinner=new AVLoadingIndicatorView(this.getApplicationContext());


        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer count=new CountDownTimer(3000,5000) {
                    @Override
                    public void onTick(long millisUntilFinished){
                        progressbar.show();

                    }

                    @Override
                    public void onFinish() {

                        progressbar.hide();
                    }
                }.start();
            }
        });

        //ALERT DIALOG
        Button dialog=findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });




        //SNACKBAR
        CoordinatorLayout layout=findViewById(R.id.coordinate);
        Button snack=findViewById(R.id.snackbar);

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });

    //DATEPICKER
        Button button=(Button)findViewById(R.id.datepicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker=new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(),"date picker");

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Button button=(Button)findViewById(R.id.datepicker);
        button.setText(currentDateString);

    }

    public void showSnackbar(){

        Snackbar snackbar=Snackbar.make(findViewById(android.R.id.content),"Had a snack at Snackbar",Snackbar.LENGTH_INDEFINITE)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1=Snackbar.make(findViewById(android.R.id.content),"Undo successful",Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }

    public void showDialog(){
        dialogClass dialog1=new dialogClass();
        dialog1.show(getSupportFragmentManager(),"example dialog");


    }

    public static class dialogClass extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("STAY HOME STAY SAFE")
                    .setMessage("Are you enjoying being quarantined?")
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(),"GOOD!",Toast.LENGTH_LONG).show();
                        }
                    })
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(),"GOOD!",Toast.LENGTH_LONG).show();
                        }

                    });


            return builder.create();
        }
    }
}
