package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

class MyView extends View {
    Context context;
    Paint p;
    int x = 0;

    public MyView(Context context) {
        super(context);
        init();
    }
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        //super.onDraw(canvas);
        p.setColor(Color.WHITE);
        p.setStrokeWidth(30);
//        canvas.drawLine(0,0,400,400, p);
//        p.setColor(Color.YELLOW);
//        float[] points = {400,400,500,200,500,200,600,400};
//        canvas.drawLines(points, p);
//        p.setFlags(Paint.ANTI_ALIAS_FLAG);
//        canvas.drawRect(400,400,600,800,p);
//        p.setColor(Color.BLACK);
//        canvas.drawCircle(500,100,100,p);

        //canvas.drawArc(100,100,150,true,p);

            canvas.drawArc(400,400,1000,1000,x, 30, true, p);
            canvas.drawArc(400,400,1000,1000,x+120, 30, true, p);
            canvas.drawArc(400,400,1000,1000,x+240, 30, true, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x += 10;
                invalidate(); // redraw the view again
                break;

            case MotionEvent.ACTION_UP:
                break;


        }
        return true;
    }
}
