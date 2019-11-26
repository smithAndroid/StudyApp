package com.smith.uimodule.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static android.animation.ValueAnimator.RESTART;
import static android.view.animation.Animation.INFINITE;

public class MyView extends View {
    private Paint mPaint, mPaint1, mPaint2, mPaintBt,mPaintEraser,mFullingPaint;
    private Canvas canvasEraser;
    private int screenWidth, screenHeight;
    private Rect rect;
    private RectF rectF;
    private float sweepAngle;
    private String text = "0%";
    private Path mPath, pathCirc;
    private Bitmap bitmap, bitmapDist,bitmapEraser;
    private Matrix matrix;
    private Camera camera = new Camera();

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        InputStream in = null;
        try {
            in = getContext().getAssets().open("robot1.jpg");
            bitmap = BitmapFactory.decodeStream(in);
            in = getContext().getAssets().open("wechat_icon.png");
            bitmapDist = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        matrix = new Matrix();
        screenWidth = getScreenWidth();
        screenHeight = getScreenHeight();
        StringBuilder sb = new StringBuilder();
        sb.append("---screenWidth---").append(screenWidth).append(" ; ----screenHeight--").append(screenHeight);
        Log.i("smith", sb.toString());
        mPaint = getPaint(Color.BLUE, 20);
        mPaint.setStyle(Paint.Style.FILL);
        LinearGradient linearGradient = new LinearGradient(0, 0, screenWidth, screenWidth,
                Color.RED, Color.BLACK, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        mPaintBt = getPaint(Color.RED, 20);
        mPaintBt.setStyle(Paint.Style.FILL);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        BitmapShader bitmapShaderDist = new BitmapShader(bitmapDist, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaintBt.setShader(bitmapShader);
        mPaint1 = getPaint(Color.RED, 20);
        mPaint2 = getPaint(Color.RED, 2);
        mPaint2.setTextSize(50);
        rectF = new RectF(0, 0, screenWidth, screenWidth);
        mPath = getPath();
        pathCirc = new Path();
        pathCirc.addCircle(screenWidth / 2, screenHeight / 2, bitmap.getWidth(), Path.Direction.CW);
        rect = new Rect(screenWidth / 2 - bitmap.getWidth() / 4, screenHeight / 2 - bitmap.getWidth() / 4, screenWidth / 2 + bitmap.getWidth() / 4, screenHeight / 2 + bitmap.getWidth() / 4);
//                startAnimation();
        Shader shader = new ComposeShader(bitmapShaderDist, bitmapShader, PorterDuff.Mode.SRC_IN);
        mPaint.setShader(shader);

        mPaintEraser = new Paint();
        mPaintEraser.setAntiAlias(true);
        mPaintEraser.setColor(0xFFFFFFFF);
        mPaintEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        bitmapEraser = Bitmap.createBitmap(getScreenWidth(),getScreenHeight(), Bitmap.Config.ARGB_8888);

        canvasEraser = new Canvas(bitmapEraser);
        mFullingPaint = new Paint();
        mFullingPaint.setColor(Color.BLACK);
        mFullingPaint.setAlpha(155);

    }

    private Path getPath() {

        Path path = new Path();
        path.moveTo(screenWidth / 2, screenWidth / 2);
//        path.lineTo(screenWidth/2+200,screenHeight/2+200);
        path.rLineTo(200, 200);
        path.rLineTo(200, 400);
        path.close();
        return path;
    }

    private Paint getPaint(int color, int strokeWidth) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
//        mPaint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    private DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    private int getScreenHeight() {
        DisplayMetrics displayMetrics = getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0,0,getScreenWidth(),getScreenHeight(),mPaint);//直线
        canvas.save();
//        canvas.clipRect(rect, Region.Op.INTERSECT);
        canvas.clipPath(pathCirc, Region.Op.DIFFERENCE);
        canvas.drawCircle(screenWidth / 2, screenHeight / 2, screenWidth / 2, mPaint);
        canvas.restore();

//        canvas.drawArc(rectF,90,sweepAngle,false,mPaint1);
//        canvas.drawText(text,screenWidth/2,screenWidth/2,mPaint2);
//        canvas.drawBitmap(bitmap,matrix,mPaint);
//        canvas.drawPath(mPath,mPaint);
//        canvas.drawCircle(screenWidth/4,screenHeight - screenWidth/2,bitmap.getWidth()/2,mPaintBt);
//        canvas.drawCircle(screenWidth/2,screenHeight/2,screenWidth/2,mPaintBt);
        canvas.drawBitmap(bitmap,screenWidth/2-bitmap.getWidth()/2,screenHeight/2-bitmap.getHeight()/2,mPaint);
        canvas.save();
        canvas.rotate(sweepAngle,screenWidth/2,screenHeight/2);
        canvas.drawBitmap(bitmap,screenWidth/2-bitmap.getWidth()/2,screenHeight/2-bitmap.getHeight()/2,mPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(bitmap.getWidth(),bitmap.getHeight());
        canvas.drawBitmap(bitmap,screenWidth/2-bitmap.getWidth()/2,screenHeight/2-bitmap.getHeight()/2,mPaint);
        canvas.restore();
        bitmapEraser.eraseColor(Color.TRANSPARENT);
        canvasEraser.drawColor(mFullingPaint.getColor());
        canvasEraser.drawCircle(screenWidth/2,screenHeight/2,screenWidth/8,mPaintEraser);
        canvas.drawBitmap(bitmapEraser,0,0,null);

    }

    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360)
                .setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                float percent = new BigDecimal((sweepAngle * 100) / 360).setScale(0, RoundingMode.HALF_UP).floatValue();
                text = new StringBuilder().append(percent).append("%").toString();
                invalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(RESTART);
        animator.setRepeatCount(INFINITE);
        animator.start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
