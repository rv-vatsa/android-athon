package com.example.rvijaykumarshahi.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by r.vijaykumarshahi on 5/15/2016.
 */
public class CircularProgress extends View {
    private int progress;
    private float angle;
    private int maximum_progress = 24;
    private int textSize ;
    private RectF rectF;
    private float startAngle;
    private int min;
    private float PADDING = 20;
    private float bottom;
    private float right;
    private float top;
    private float left;
    private int circleTxtPadding;
    private Paint backgroundPaint,foregroundPaint, textPaint;
    private float backgroundStrokeWidth;
    private float strokeWidth;
    private int height, width;
    private Context context;
    private int backgroundPaintColor;
    private int foregroundPaintColor;
    private int textPaintColor,textStrokeWidth;



    public CircularProgress(Context context) {
        super(context);
        init(context);
    }

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context,attrs);
        init(context);

    }
    public void initAttributeSet(Context context,AttributeSet attrs){

        TypedArray typedArray = context.getTheme().obtainStyledAttributes( attrs, R.styleable.CircularProgressView, 0, 0);
        progress = (int) typedArray.getFloat(R.styleable.CircularProgressView_initialProgressValue, progress);
        strokeWidth = typedArray.getDimension(R.styleable.CircularProgressView_stroke_width, strokeWidth);
        backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircularProgressView_background_stroke_width, backgroundStrokeWidth);
        textSize = typedArray.getInt(R.styleable.CircularProgressView_text_size, textSize);
        textStrokeWidth = typedArray.getInt(R.styleable.CircularProgressView_textStrokeWidth,textStrokeWidth);
        typedArray.recycle();
    }

    protected void init(Context ctx) {
        context = ctx;
        rectF = new RectF();
        setProgress(0);






        backgroundPaintColor = ContextCompat.getColor(context, R.color.colorBackgroundPaint);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundPaintColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintColor = ContextCompat.getColor(context, R.color.colorTextPaint);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setStrokeWidth(textStrokeWidth);
        textPaint.setTextSize(textSize);

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaintColor = ContextCompat.getColor(context, R.color.colorForegroundPaint);
        foregroundPaint.setColor(foregroundPaintColor);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);

    }

    public void setProgress(int progress) {
        setProgressInView(progress);

    }
    private synchronized void setProgressInView(int progress) {
        this.progress = progress;
        invalidate();

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        startAngle = 270;
        angle = 360 * progress / maximum_progress;
        canvas.drawArc(rectF, startAngle, 360, false, backgroundPaint); // Initially circle painted in background color

        if (progress > 0)
            canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);
        drawText(canvas);
    }

    private void drawText(Canvas canvas){

        Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setStrokeWidth(1);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setTextSize(55);
        String text;
        mPaint1.setColor(Color.BLUE);
        mPaint1.setTextAlign(Paint.Align.CENTER);
        if(progress >= 12){
            text = progress % 12 + "" + "PM";
        }else{
            text = progress % 12 + "" + "AM";
        }
        if(progress == maximum_progress && progress % 12 == 0 ){
            text = progress/2 + "PM";
        }

        if(progress == maximum_progress/2 && progress % 12 == 0 ){
            text = progress + "PM";
        }
        float x = (rectF.left + rectF.right) / 2;
        float y = (rectF.top + rectF.bottom)/2;
        canvas.drawText(text, x, y, mPaint1);
    }
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        min = setDimensions(widthMeasureSpec, heightMeasureSpec);
        left = 50 +strokeWidth;
        top = 100 + strokeWidth;
        right = min - strokeWidth;
        bottom = min - strokeWidth;
        rectF.set(left + PADDING , top + PADDING , right - PADDING, bottom
                - PADDING);
    }

    protected int setDimensions(int widthMeasureSpec, int heightMeasureSpec) {
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        final int smallerDimens = Math.min(width, height);

        setMeasuredDimension(smallerDimens, smallerDimens);
        return smallerDimens;
    }
}
