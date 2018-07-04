package com.iigo.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/28 0028 12:23
 */
public class BezerView extends View {
    private String TAG = "BezerView";

    private List<PointF> definedPoints = new ArrayList<>(); //随机生成的坐标点
    private List<PointF> bezerPoints; //贝塞尔曲线的坐标点

    private Paint bezerCurvePaint; //贝塞尔曲线画笔
    private Paint coordinateLinePaint; //坐标点画笔
    private Paint coordinatePointPaint; //坐标间连线画笔

    private Path linePath; //几点之间的连线path
    private Path curvePath; //曲线path
    public BezerView(Context context) {
        super(context);

        init();
    }

    public BezerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BezerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        coordinateLinePaint = new Paint();
        coordinateLinePaint.setAntiAlias(true);
        coordinateLinePaint.setColor(Color.RED);
        coordinateLinePaint.setStrokeWidth(4);
        coordinateLinePaint.setStyle(Paint.Style.STROKE);

        coordinatePointPaint = new Paint();
        coordinatePointPaint.setAntiAlias(true);
        coordinatePointPaint.setStrokeWidth(4);
        coordinatePointPaint.setStyle(Paint.Style.STROKE);
        coordinatePointPaint.setColor(Color.RED);

        bezerCurvePaint = new Paint();
        bezerCurvePaint.setAntiAlias(true);
        bezerCurvePaint.setStrokeWidth(8);
        bezerCurvePaint.setStyle(Paint.Style.STROKE);
        bezerCurvePaint.setColor(Color.BLACK);


        linePath = new Path();
        curvePath = new Path();
    }

    //这里随机产生五个点
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        initPoints();
    }

    //初始化坐标点
    private void initPoints(){
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (definedPoints.size() > 0){
            definedPoints.clear();
        }

        //这里可以自定义为几阶曲线,下面定义为四阶
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width / 2) + 100;
            int y = random.nextInt(height / 2) + 100;

            PointF pointF = new PointF(x, y);
            definedPoints.add(pointF);
        }

        bezerPoints = DeCasteljau.create().setPoints(definedPoints).execute(1f / 1000f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int size = definedPoints.size();

        linePath.reset();

        //设置默认坐标原点为第一个点
        linePath.moveTo(definedPoints.get(0).x, definedPoints.get(0).y);

        for (int i = 0; i < size; i++) {
            PointF point = definedPoints.get(i);
            linePath.lineTo(point.x, point.y); //设定到x,y的直线
            canvas.drawPath(linePath, coordinateLinePaint); //画线

            // 控制点
            canvas.drawCircle(point.x, point.y, 10, coordinatePointPaint);
        }

        if (bezerPoints == null || bezerPoints.isEmpty()){
            return;
        }

        curvePath.reset();

        //设置默认坐标原点为第一个点
        curvePath.moveTo(bezerPoints.get(0).x, bezerPoints.get(0).y);
        for (int i = 0; i < bezerPoints.size(); i++){
            curvePath.lineTo(bezerPoints.get(i).x, bezerPoints.get(i).y);
        }

        canvas.drawPath(curvePath, bezerCurvePaint);
    }

    public void refresh(){
        initPoints();
        invalidate();
    }
}
