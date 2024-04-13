package com.example.chefmate;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class Circle {
    private final Button circle1;
    private final Button circle2;
    private final Button circle3;
    public Circle(Button circle1,Button circle2,Button circle3){
        this.circle1=circle1;
        this.circle2=circle2;
        this.circle3=circle3;
    }
    public void stateCircle(int layoutId){
        switch (layoutId) {
            case 1:

                circle1.setEnabled(true);
                circle2.setEnabled(false);
                circle3.setEnabled(false);
                break;
            case 2:

                circle2.setEnabled(true);
                circle3.setEnabled(false);
                break;
            case 3:

                circle3.setEnabled(true);
                break;
        }
    }
}
