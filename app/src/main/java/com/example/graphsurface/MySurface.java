package com.example.graphsurface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private float startX = -50;
    private float startY = -50;
    private float stopX = 50;
    private float stopY = 50;

    public MySurface(Context context) {
        super(context);

        surfaceHolder = getHolder();

        paint = new Paint();
        paint.setColor(Color.RED);

        // this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // paint.setStyle(Style.FILL);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawGraph();
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        paint = null;

    }
    public void drawGraph()
    {
        surfaceHolder = getHolder();

        // Get and lock canvas object from surfaceHolder.
        Canvas canvas = surfaceHolder.lockCanvas();

        Paint surfaceBackground = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.BLACK);
        // Draw the surfaceview background color.
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        // Draw the circle.
        paint.setColor(Color.RED);
        canvas.drawLine(startX, startY, stopX, stopY, paint);

        canvas.drawCircle(startX, startY, 10, paint);

        // Unlock the canvas object and post the new draw.
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
    public void setStartX(float x){this.startX = x; this.stopX = -x;}
    public void setStartY(float y){this.startY = y; this.stopY = -y;}
    public double getDistance(){return Math.sqrt(Math.pow(stopX-startX,2) + Math.pow(stopY-startY, 2));}
    public float getX(){return startX;}
    public float getY(){return -startY;}
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

}
