package com.example.rezakala.Comments;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.rezakala.Action.Convert_px;
import com.example.rezakala.R;

public class Add_Comments extends AppCompatActivity {
    LinearLayout liner_possion_0_1,liner_possion_1_1,liner_possion_1_2;
    int defult_value=90;
    int idview_1[],idview_2[],idview_3[];
    Custom_Seekbar seekbar_1,seekbar_2,seekbar_3;
    TextView Tv_number_1,Tv_number_2,Tv_number_3;
    int progress_1=3,progress_2=3,progress_3;
    Button Btn_add_scoreback;
    private static final String TAG = "Add_Comments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments);

        Cast();
        Get_Load_1();
        Get_Load_2();
        Get_Load_3();

        seekbar_1.setOnSeekBarChangeListener
                (new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                progress_1=progress;
                Tv_number_1.setText(String.valueOf(progress));
                Set_Change_Progress_seekbar(progress,1);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_2=progress;
                Tv_number_2.setText(String.valueOf(progress));
                Set_Change_Progress_seekbar(progress,2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_3=progress;
                Tv_number_3.setText(String.valueOf(progress));
                Set_Change_Progress_seekbar(progress,3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Btn_add_scoreback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    void Set_Change_Progress_seekbar(int progress,int check)
    {
        int px = getResources().getDisplayMetrics().widthPixels;
        int t = Convert_px.Convert_px(defult_value, Add_Comments.this);
        int count = (px - t) / 5;
        String Defalt_color = "#ECF0F1";
        String Color_2 = "#2C3E50";
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(count, -2);
        layoutParams.setMargins(10, 0, 10, 0);
        View view1 = null;
        View view2 = null;
        View view3 = null;
        View view4 = null;
        View view5 = null;

        for (int i = 0; i < progress; i++) {
            if(check==1)
            {
                view1 = findViewById(idview_1[0]);
                view2 = findViewById(idview_1[1]);
                view3 = findViewById(idview_1[2]);
                view4 = findViewById(idview_1[3]);
                view5 = findViewById(idview_1[4]);
            }
            else if(check==2)
            {
                view1 = findViewById(idview_2[0]);
                view2 = findViewById(idview_2[1]);
                view3 = findViewById(idview_2[2]);
                view4 = findViewById(idview_2[3]);
                view5 = findViewById(idview_2[4]);
            }
            else if(check==3){
                view1 = findViewById(idview_3[0]);
                view2 = findViewById(idview_3[1]);
                view3 = findViewById(idview_3[2]);
                view4 = findViewById(idview_3[3]);
                view5 = findViewById(idview_3[4]);
            }

            switch (i)
            {
                case 0:
                    view1.setLayoutParams(layoutParams);
                    view1.setBackgroundColor(Color.parseColor(Color_2));
                    view2.setBackgroundColor(Color.parseColor(Defalt_color));
                    view3.setBackgroundColor(Color.parseColor(Defalt_color));
                    view4.setBackgroundColor(Color.parseColor(Defalt_color));
                    view5.setBackgroundColor(Color.parseColor(Defalt_color));
                    break;

                case 1:
                    view2.setLayoutParams(layoutParams);
                    view2.setBackgroundColor(Color.parseColor(Color_2));
                    view3.setBackgroundColor(Color.parseColor(Defalt_color));
                    view4.setBackgroundColor(Color.parseColor(Defalt_color));
                    view5.setBackgroundColor(Color.parseColor(Defalt_color));
                    break;

                case 2:
                    view3.setLayoutParams(layoutParams);
                    view3.setBackgroundColor(Color.parseColor(Color_2));
                    view4.setBackgroundColor(Color.parseColor(Defalt_color));
                    view5.setBackgroundColor(Color.parseColor(Defalt_color));
                    break;
                case 3:
                    view4.setLayoutParams(layoutParams);
                    view4.setBackgroundColor(Color.parseColor(Color_2));
                    view5.setBackgroundColor(Color.parseColor(Defalt_color));
                    break;

                case 4:
                    view5.setLayoutParams(layoutParams);
                    view5.setBackgroundColor(Color.parseColor(Color_2));
                    break;
            }
        }

        if(progress==0){
            for (int i = 0; i <5 ; i++)
            {
                if(check==1){
                    View view = findViewById(idview_1[i]);
                    view.setLayoutParams(layoutParams);
                    view.setBackgroundColor(Color.parseColor(Defalt_color));
                }else if (check==2)
                {
                    View view = findViewById(idview_2[i]);
                    view.setLayoutParams(layoutParams);
                    view.setBackgroundColor(Color.parseColor(Defalt_color));
                }else if(check==3){
                    View view = findViewById(idview_3[i]);
                    view.setLayoutParams(layoutParams);
                    view.setBackgroundColor(Color.parseColor(Defalt_color));
                }

            }
        }
    }

    void Cast()
    {
        liner_possion_0_1=findViewById(R.id.liner_possion_0_1);
        seekbar_1=findViewById(R.id.seekbar_1);
        Tv_number_1=findViewById(R.id.Tv_number_1);
        liner_possion_1_1=findViewById(R.id.liner_possion_1_1);
        liner_possion_1_2=findViewById(R.id.liner_possion_1_2);
        seekbar_2=findViewById(R.id.seekbar_2);
        Tv_number_2=findViewById(R.id.Tv_number_2);
        seekbar_3=findViewById(R.id.seekbar_3);
        Tv_number_3=findViewById(R.id.Tv_number_3);
        Btn_add_scoreback=findViewById(R.id.Btn_add_scoreback);

    }
    void Get_Load_1()
    {
        int px=getResources().getDisplayMetrics().widthPixels;
        int t= Convert_px.Convert_px(defult_value,Add_Comments.this);
        int count=(px-t)/5;
        idview_1=new int[5];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(count,-2);
        layoutParams.setMargins(10,0,10,0);
        for (int i = 0; i <5 ; i++) {
            int id= View.generateViewId();
            View view=new View(Add_Comments.this);
            idview_1[i]=id;
            view.setBackgroundColor(Color.parseColor("#ECF0F1"));
            view.setLayoutParams(layoutParams);
            view.setId(id);
            liner_possion_0_1.addView(view);
        }
    }

    void Get_Load_2()
    {
        int px=getResources().getDisplayMetrics().widthPixels;
        int t= Convert_px.Convert_px(defult_value,Add_Comments.this);
        int count=(px-t)/5;
        idview_2=new int[5];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(count,-2);
        layoutParams.setMargins(10,0,10,0);
        for (int i = 0; i <5 ; i++) {
            int id= View.generateViewId();
            View view=new View(Add_Comments.this);
            idview_2[i]=id;
            view.setBackgroundColor(Color.parseColor("#ECF0F1"));
            view.setLayoutParams(layoutParams);
            view.setId(id);
            liner_possion_1_1.addView(view);
        }
    }

    void Get_Load_3()
    {
        int px=getResources().getDisplayMetrics().widthPixels;
        int t= Convert_px.Convert_px(defult_value,Add_Comments.this);
        int count=(px-t)/5;
        idview_3=new int[5];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(count,-2);
        layoutParams.setMargins(10,0,10,0);
        for (int i = 0; i <5 ; i++) {
            int id= View.generateViewId();
            View view=new View(Add_Comments.this);
            idview_3[i]=id;
            view.setBackgroundColor(Color.parseColor("#ECF0F1"));
            view.setLayoutParams(layoutParams);
            view.setId(id);
            liner_possion_1_2.addView(view);
        }
    }
}