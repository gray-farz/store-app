package com.example.rezakala.Comments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rezakala.Config;
import com.example.rezakala.More_Product.Datamodel_score;
import com.example.rezakala.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Activity_Comments extends AppCompatActivity implements Config {
    ImageView Im_close;
    TextView Tv_error_handel;
    FloatingActionButton float_action_btn_sendcomments;
    LinearLayout liner_header,liner_items;
    Intent getintent;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Im_close=findViewById(R.id.Im_close);
        Tv_error_handel=findViewById(R.id.Tv_error_handel);
        float_action_btn_sendcomments=findViewById(R.id.float_action_btn_sendcomments);
        liner_header=findViewById(R.id.liner_header);
        liner_items=findViewById(R.id.liner_items);
        Im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getintent=getIntent();
        title=getintent.getStringExtra("title");
        final String id=getintent.getStringExtra("id");
        float_action_btn_sendcomments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Add_Comments.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

}