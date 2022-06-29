package com.example.rezakala.Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezakala.Action.Getinfo;
import com.example.rezakala.Panel.Api.Api_panel;
import com.example.rezakala.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public class Login_Activity extends AppCompatActivity {
    TextView Tv_title,Tv_forget_password,Tv_insert;
    CheckBox checkbox;
    EditText Et_password,Et_phone;
    ImageView Im_close,Im_search,Im_login;
    Api_panel api_panel;
    ProgressWheel progress_wheel;
    private static final String TAG = "aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Cast();
        api_panel=new Api_panel(this,progress_wheel);
        Getinfo.getinfo(Login_Activity.this);
    }

    void Cast()
    {
        Tv_title=findViewById(R.id.Tv_title);
        progress_wheel=findViewById(R.id.progress_wheel);
        Im_login=findViewById(R.id.Im_login);
        Tv_insert=findViewById(R.id.Tv_insert);
        Et_password=findViewById(R.id.Et_password);
        Et_phone=findViewById(R.id.Et_phone);
        Im_close=findViewById(R.id.Im_close);
        Tv_forget_password=findViewById(R.id.Tv_forget_password);
        checkbox=findViewById(R.id.checkbox);
        Im_search=findViewById(R.id.Im_search);
        Tv_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(intent);
            }
        });
//        Tv_forget_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Et_phone.getText().toString().trim().isEmpty()){
//                    Et_phone.setError("لطفا شماره موبایل را وارد کنید...");
//                }else {
//                    api_panel.Get_send_code_reply(Et_phone.getText().toString());
//                }
//            }
//        });
        Im_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Im_login: ");
                if(Et_phone.getText().toString().trim().isEmpty()){
                    Et_phone.setError("لطفا شماره موبایل یا ایمیل را وارد کنید...");
                    Et_phone.requestFocus();

                }else if (Et_password.getText().toString().trim().isEmpty()){
                    Et_password.setError("لطفا رمز عبور را وارد کنید...");
                    Et_password.requestFocus();
                }
                else
                {
                    api_panel.Get_login(Et_phone.getText().toString(),Et_password.getText().toString());
                }
            }
        });
//        Tv_title.setText("ورود");
//        Im_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        Im_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), Search_Product.class);
//                startActivity(intent);
//            }
//        });
//
//        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    Et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                }
//                else
//                {
//                    Et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });
    }
}