package com.example.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class FanView extends SurfaceView implements Runnable{ // we do it from java code and if it has more than one attribute, then we do it by xml code
    Context ct;
    SurfaceHolder surfaceHolder;
    Paint p;
    Canvas canvas;
    Thread thread = null;
    boolean isRotating;
    int x = 0;
    Rect rect;

    public FanView(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        this.ct = ct;

        surfaceHolder = getHolder();
        p = new Paint();


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                startThread();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }

    public void startThread() {
        isRotating = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopThread() {
        isRotating = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (surfaceHolder.getSurface().isValid()) {
            canvas.drawColor(Color.GRAY);
            p.setColor(Color.YELLOW);

            canvas.drawArc(300, 300, 1000, 1000, x, 30, true, p);
            canvas.drawArc(300, 300, 1000, 1000, x + 120, 30, true, p);
            canvas.drawArc(300, 300, 1000, 1000, x + 240, 30, true, p);

            x += 10;
            rect = new Rect(300,300,1000,1000);
        }

    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (isRotating) {
            canvas = surfaceHolder.lockCanvas();
            synchronized (this) {
                onDraw(canvas);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x1 = (int) event.getX();
        int y1 = (int) event.getY();

        if (rect.contains(x1, y1)) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startThread();
                    break;

                case MotionEvent.ACTION_UP:
                    stopThread();
                    break;


            }
        }
        return true;
    }
}
