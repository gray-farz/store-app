package com.example.rezakala.Comments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class Custom_Seekbar extends androidx.appcompat.widget.AppCompatSeekBar {

    private Paint textPaint;
    private Rect textBounds=new Rect();
    private  String text="";

    public Custom_Seekbar(Context context) {
        super(context);
        textPaint=new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setPathEffect(new
                DashPathEffect(new float[] {10,10},0));
    }

    public Custom_Seekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint=new Paint();
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setPathEffect(new DashPathEffect(new float[] {10,10},
                0));
        textPaint.setColor(Color.BLACK);
    }

    public Custom_Seekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textPaint=new Paint();
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setPathEffect(new DashPathEffect(new float[] {10,10},0));
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int progress=getProgress();
        text=progress+"";
        float width=getWidth();
        float height=getHeight();
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        textPaint.setTextSize(40);
        textPaint.getTextBounds(text,0,text.length(),textBounds);
        float possion=(width/getMax()) * getProgress();
        float textstart=possion-textBounds.centerX();
        float textend=possion+textBounds.centerX();
        if(textstart<=1) textstart=20;
        if(textend >width){
            textstart-=(textend-width+30);
        }
        float ypossion=height;
        canvas.drawText(text,textstart,0,textPaint);
    }
    public synchronized void setTextColor(int color){
        super.drawableStateChanged();
        textPaint.setColor(color);
        textPaint.setPathEffect(new DashPathEffect(new float[] {10,10},0));
        drawableStateChanged();

    }
}
