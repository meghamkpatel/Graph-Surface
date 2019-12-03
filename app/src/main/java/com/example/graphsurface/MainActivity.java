package com.example.graphsurface;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private TextView displayText = null;
    private Button start = null;
    private LinearLayout canvasLayout = null;

    MySurface customSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();

        getSupportActionBar().hide();
        displayText = (TextView)findViewById(R.id.displayinfo);
        // Create custom surfaceview object.
        customSurfaceView = new MySurface(getApplicationContext());

        // Set this as the onTouchListener to process custom surfaceview ontouch event.
        customSurfaceView.setOnTouchListener(this);

        // Add the custom surfaceview object to the layout.
        canvasLayout.addView(customSurfaceView);
    }

    private void initControls(){
        if(canvasLayout == null)
        {
            canvasLayout = (LinearLayout)findViewById(R.id.customViewLayout);
        }
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        displayText.setText("Distance: " +customSurfaceView.getDistance() + "\n(" + customSurfaceView.getX() + "," + customSurfaceView.getY() + ")");
        // If user touch the custom SurfaceView object.
        if(view instanceof SurfaceView) {
            view.invalidate();

            float x = motionEvent.getX();
            float y = motionEvent.getY();

            customSurfaceView.setStartX(x);

            customSurfaceView.setStartY(y);
            // Create and set a red paint to custom surfaceview.
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            customSurfaceView.setPaint(paint);

            customSurfaceView.drawGraph();

            // Tell android os the onTouch event has been processed.
            return true;
        }
        else{
            // Tell android os the onTouch event has not been processed.
            return false;
        }
    }
}
