package com.mr2.zaiko.ui.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class CrossDirectionFlickCheck {
    public static final int LEFT_FLICK= 0;
    public static final int RIGHT_FLICK = 1;
    public static final int UP_FLICK = 2;
    public static final int DOWN_FLICK = 3;
    public enum Restraint{
        NONE,
        VERTICAL,
        HORIZONTAL
    }

    private float restraintBoundaryX = 30.0f;
    private float restraintBoundaryY = 30.0f;
    private float actionBoundaryX = 150.0f;
    private float actionBoundaryY = 150.0f;
    private float originX;
    private float originY;
    private float nowX;
    private float nowY;
    private Restraint restraint = Restraint.NONE;

    public CrossDirectionFlickCheck(View view) {

        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        originX = event.getX();
                        originY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        nowX = event.getX();
                        nowY = event.getY();
                        if (restraint != Restraint.NONE) checkAction();
                        v.performClick();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        nowX = event.getX();
                        nowY = event.getY();
                        checkRestraint();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });
    }

    private void checkRestraint(){
        //水平
        if (nowX > originX + restraintBoundaryX ||
                originX - restraintBoundaryX > nowX){
            onRestrain(Restraint.HORIZONTAL);
            restraint = Restraint.HORIZONTAL;
            return;
        }
        //垂直
        if (nowY > originY + restraintBoundaryY ||
                originY - restraintBoundaryY > nowY){
            onRestrain(Restraint.VERTICAL);
            restraint = Restraint.VERTICAL;
            return;
        }
        onRestrain(Restraint.NONE);
        restraint = Restraint.NONE;
    }

    private void checkAction(){
        switch (restraint){
            case NONE:
                return;
            case HORIZONTAL:
                if (nowX > originX + actionBoundaryX) {
                    onFlick(RIGHT_FLICK);
                    return;
                }
                if (originX - actionBoundaryX > nowX){
                    onFlick(LEFT_FLICK);
                }
                return;
            case VERTICAL:
                if (nowY > originY + actionBoundaryY){
                    onFlick(DOWN_FLICK);
                    return;
                }
                if (originY - actionBoundaryY > nowY){
                    onFlick(UP_FLICK);
                }
        }
    }

    public abstract void onRestrain(Restraint restraintDirection);

    public abstract void onFlick(int swipe);
}
