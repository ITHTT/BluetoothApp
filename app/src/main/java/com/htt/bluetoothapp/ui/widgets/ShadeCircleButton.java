package com.htt.bluetoothapp.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.htt.bluetoothapp.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ShadeCircleButton extends View {
    /**默认半径大小*/
    private static final int DEFAULT_CIRCLE_RADIUS = 60;
    private static final int DEFAULT_TEXT_SIZE=64;
    /**内部圆*/
    private Paint mPaintInnerCircle;
    private Paint mPaintText;
    private Paint mPaintOuterCircle;
    private float centerX;
    private float centerY;

    private int outerStrokeWidth=10;
    private int shadeRadiusUtil=1;
    private int shadeRadiusSize=0;
    private int shadeAlpha=0;
    private int shadeAlphaUtil=5;

    private int innerCircleColor=Color.parseColor("#008000");
    private int outerCircleColor=Color.parseColor("#008000");
    private int textColor= Color.parseColor("#ffffff");
    private String text;

    private int circleRadius=DEFAULT_CIRCLE_RADIUS;
    private float textSize=DEFAULT_TEXT_SIZE;

    private Handler handler = new Handler();
    private Runnable run = new Runnable()
    {
        @Override
        public void run()
        {
            if(shadeRadiusSize==0){
                shadeRadiusUtil=1;
                shadeAlphaUtil=15;
            }else if(shadeRadiusSize==17){
                shadeRadiusUtil=-1;
                shadeAlphaUtil=-15;
            }
            shadeRadiusSize+=shadeRadiusUtil;
            shadeAlpha+=shadeAlphaUtil;
            postInvalidate();
            handler.postDelayed(run, 100);
        }
    };

    public ShadeCircleButton(Context context) {
        super(context);
        initViews(context,null);
    }

    public ShadeCircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context,attrs);
    }

    public ShadeCircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShadeCircleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context,attrs);
    }

    private void initViews(Context context,AttributeSet attrs){
        if(attrs!=null){
            TypedArray ta = context.obtainStyledAttributes(attrs,
                    R.styleable.ShadeCircleButton);
            innerCircleColor=ta.getColor(R.styleable.ShadeCircleButton_innerCircleColor,innerCircleColor);
            outerCircleColor=ta.getColor(R.styleable.ShadeCircleButton_outerCircleColor,outerCircleColor);
            textColor=ta.getColor(R.styleable.ShadeCircleButton_textColor,textColor);
            text=ta.getString(R.styleable.ShadeCircleButton_buttonText);
            circleRadius=ta.getDimensionPixelSize(R.styleable.ShadeCircleButton_circleRadius, circleRadius);
            textSize=ta.getDimension(R.styleable.ShadeCircleButton_textSize, textSize);
            ta.recycle();
        }

        mPaintInnerCircle=new Paint();
        mPaintInnerCircle.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintInnerCircle.setColor(innerCircleColor);
        mPaintInnerCircle.setStrokeWidth(2);
        mPaintInnerCircle.setAntiAlias(true);

        mPaintOuterCircle=new Paint();
        mPaintOuterCircle.setStyle(Paint.Style.STROKE);
        mPaintOuterCircle.setColor(outerCircleColor);
        mPaintOuterCircle.setStrokeWidth(outerStrokeWidth);
        mPaintOuterCircle.setAntiAlias(true);

        mPaintText=new Paint();
        mPaintText.setColor(textColor);
        mPaintText.setTextSize(textSize);
        mPaintText.setAntiAlias(true);

        handler.post(run);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2*circleRadius+outerStrokeWidth,2*circleRadius+outerStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**绘制内圆*/
        canvas.drawCircle(centerX,centerY,circleRadius-outerStrokeWidth-shadeRadiusSize,mPaintInnerCircle);

        mPaintOuterCircle.setAlpha(shadeAlpha);
        canvas.drawCircle(centerX,centerY,circleRadius-shadeRadiusSize,mPaintOuterCircle);

        drawText(canvas);
    }

    protected void drawText(Canvas canvas){
        float textWidth=textSize*text.length();
        float left=centerX-textWidth/2;
        Paint.FontMetrics fontMetrics=mPaintText.getFontMetrics();
        float height=fontMetrics.ascent+fontMetrics.descent+fontMetrics.leading;
        canvas.drawText(text,left,centerY-height/2,mPaintText);
    }

    public void setText(String text){
        this.text=text;
        postInvalidate();
    }
}
