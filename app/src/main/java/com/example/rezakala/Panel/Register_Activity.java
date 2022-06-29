package com.example.rezakala.Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezakala.Action.Getinfo;
import com.example.rezakala.Panel.Api.Api_panel;
import com.example.rezakala.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public class Register_Activity extends AppCompatActivity {
    TextView Tv_title;
    ImageView Im_close,Im_search,Im_register;
    Api_panel api_panel;
    EditText Et_phone,Et_password;
    ProgressWheel progress_wheel;
    private static final String TAG = "reg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d(TAG, "onCreate: Register_Activity");

        Getinfo.getinfo(Register_Activity.this);
        progress_wheel=findViewById(R.id.progress_wheel);
        api_panel=new Api_panel(this,progress_wheel);
        Cast();

    }

    void Cast()
    {
        Tv_title=findViewById(R.id.Tv_title);
        Im_register=findViewById(R.id.Im_register);
        Im_close=findViewById(R.id.Im_close);
        Im_search=findViewById(R.id.Im_search);
        Et_phone=findViewById(R.id.Et_phone);
        Et_password=findViewById(R.id.Et_password);


        Im_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=Et_phone.getText().toString();
                String pass=Et_password.getText().toString();
                if(phone.trim().isEmpty()){
                    Et_phone.setError("لطفا شماره موبایل یا ایمیل را وارد کنید...");
                    Et_phone.requestFocus();
                }
                else if (pass.trim().isEmpty()){
                    Et_password.setError("لطفا پسورد عبور را وارد کنید...");
                    Et_password.requestFocus();
                }
                else
                {
                    Log.d(TAG, "onClick: Im_register");
                    api_panel.GetRegister_panel(phone,pass);
                }
            }
        });

        Tv_title.setText("ثبت نام");
        Im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        Im_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), Search_Product.class);
//                startActivity(intent);
//            }
//        });
    }
}