package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

   // MyView myView;
    Canvas canvas;
    ImageView iv;
    Paint p, pText;
    Rect r1,r2;
    int cBack, cRect, cCir;
    Bitmap bp;
    int factor = 50;
    int start = factor;

    FanView fanView;
    DrawingClass drawingClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //myView = new MyView(this);
        //setContentView(myView);
        fanView = new FanView(this);
        //setContentView(fanView);
        drawingClass = new DrawingClass(this);
        setContentView(drawingClass);
        iv = findViewById(R.id.iv);

        cBack = ResourcesCompat.getColor(getResources(), R.color.c1, null);
        cRect = ResourcesCompat.getColor(getResources(), R.color.c2, null);
        cCir = ResourcesCompat.getColor(getResources(), R.color.c3, null);

        p = new Paint();
        pText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
        pText.setTextSize(50);

        r1 = new Rect();
        r2 = new Rect();


    }

    @Override
    protected void onStop() {
        super.onStop();
        fanView.stopThread();
    }

    public void drawThis(View view) {

            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            int halfWidth = viewWidth / 2;
            int halfHeight = viewHeight / 2;

        if ( start == factor) {
            bp = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
            iv.setImageBitmap(bp);

            canvas = new Canvas(bp);
            canvas.drawColor(cBack);
            p.setColor(Color.WHITE);
            canvas.drawText("Keep clicking", 100, 100, pText);
            start += factor;

        }
        else{
            if(start < halfWidth && start < halfHeight) {
//                p.setColor(cRect);
                r1.set(start, start, viewWidth-start, viewHeight-start);
                p.setColor(cRect - 200*start);
                canvas.drawRect(r1, p);
                start += factor;
            }
            else{
                p.setColor(cCir);
                canvas.drawCircle(halfWidth, halfHeight, halfWidth/2, p);
//                String done = "Done!!!";
//                pText.getTextBounds(done, 0, done.length(), r2);
//                int x = halfWidth
                canvas.drawText("Done!", halfWidth-65, halfHeight-10, pText);


            }
        }
        view.invalidate();
    }
}