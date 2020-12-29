package com.tcs.assignmentfour.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tcs.assignmentfour.R;

public class PercentageInCircleView extends View {
    //circle and text colors
    private int circleColor, fontColor;
    private String text;
    int fontSize;

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
        requestLayout();
    }

    public int getFontColor() {
        return fontColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
        invalidate();
        requestLayout();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        invalidate();
        requestLayout();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
        requestLayout();
    }

    public Paint getCirclePaint() {
        return circlePaint;
    }

    public void setCirclePaint(Paint circlePaint) {
        this.circlePaint = circlePaint;
    }
    //paint for drawing custom view
    private Paint circlePaint;
    public PercentageInCircleView(Context context, AttributeSet attrs){
        super(context, attrs);
        //paint object for drawing in onDraw
        circlePaint = new Paint();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AssignmentFour, 0, 0);
        try {
            text = a.getString(R.styleable.AssignmentFour_text);
            circleColor = a.getInteger(R.styleable.AssignmentFour_circleColor, 0);
            fontColor = a.getInteger(R.styleable.AssignmentFour_fontColor, 0);
            fontSize = a.getDimensionPixelSize(R.styleable.AssignmentFour_fontSize, 0);
        } finally {
            a.recycle();
        }

    }
    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        circlePaint.setColor(fontColor);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(fontSize);
        canvas.drawText(text+"%", viewWidthHalf, viewHeightHalf+(fontSize/4), circlePaint);
    }
}
