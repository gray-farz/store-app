package com.example.rezakala.Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezakala.Panel.Api.Api_panel;
import com.example.rezakala.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.Timer;

public class Verfy_Code extends AppCompatActivity {
    private static final String TAG = "reg";
    TextView Tv_title,Tv_edit_phone,Tv_timer,phone_info;
    Intent intentget;
    ImageView Im_close,Im_search;
    int arrayid[] ={R.id.Et_1,R.id.Et_2,R.id.Et_3,R.id.Et_4,R.id.Et_5};
    Timer timer;
    long timercount;
    int check;
    ProgressWheel progress_wheel;
    Api_panel api_panel;
    String code_send="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfy_code);

        progress_wheel=findViewById(R.id.progress_wheel);
        api_panel=new Api_panel(this,progress_wheel);
        Tv_title=findViewById(R.id.Tv_title);
        Tv_timer=findViewById(R.id.Tv_timer);
        phone_info=findViewById(R.id.phone_info);
        Tv_edit_phone=findViewById(R.id.Tv_edit_phone);
        Im_close=findViewById(R.id.Im_close);
        Im_search=findViewById(R.id.Im_search);
        Tv_title.setText("تایید شماره تلفن همراه");
        intentget=getIntent();
        phone_info.setText(" کد تایید برای شماره "+intentget.getStringExtra("phone")+" ارسال شد ");
        Im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Tv_edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        for (int i = 0; i <arrayid.length ; i++) {
            int j=i+1;
            EditText editText1=findViewById(arrayid[i]);
            if(arrayid.length-1!=i){
                final EditText editText2=findViewById(arrayid[j]);
                //Log.e(TAG, "onCreate: "+editText2 );
                editText1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        editText2.requestFocus();
                    }
                });


            }
        }

        EditText editText3=findViewById(arrayid[4]);
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.d(TAG, "afterTextChanged second: ");
                code_send="";
                for (int i = 0; i <arrayid.length ; i++) {
                    EditText editText1=findViewById(arrayid[i]);
                    if(editText1.getText().toString().trim().isEmpty()){
                        Log.d(TAG, "afterTextChanged second:0 ");
                        Toast.makeText(Verfy_Code.this, "لطفا کد ارسالی را بررسی کنید...", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        //Log.d(TAG, "afterTextChanged second:1 ");
                        code_send+=editText1.getText().toString();
                        //Log.d(TAG, "afterTextChanged: "+intentget.getStringExtra("reg") );
                        api_panel.GetSendcode_Panel(intentget.getStringExtra("phone"),code_send,intentget.getStringExtra("reg"));
                    }
                }
            }
        });
    }
}