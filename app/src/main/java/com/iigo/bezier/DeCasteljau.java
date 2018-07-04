package com.iigo.bezier;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/28 0028 15:14
 */
public class DeCasteljau {
    private List<PointF> computePoints;

    private DeCasteljau(){
    }

    public static DeCasteljau create(){
        return new DeCasteljau();
    }

    /**
     * 配置坐标点数组
     *
     * 2个点代表一阶贝塞尔曲线
     *
     * 3个点代表二阶贝塞尔曲线
     *
     * ....
     *
     * n个点代表 (n - 1阶)贝塞尔曲线
     *
     * @param points 坐标点数组
     * */
    public DeCasteljau setPoints(List<PointF> points) {
        this.computePoints = points;
        return this;
    }

    /**
     * 执行获取曲线坐标
     *
     * @param intensity 密集程度 > 0 && < 1，设置曲线密集程度，例如1 / 100则是取100个点
     * @return 返回的为贝塞尔曲线的坐标数组
     * */
    public List<PointF> execute(float intensity){
        if (computePoints == null || computePoints.isEmpty()){
            return null;
        }

        int order = computePoints.size() - 1;
        List<PointF> returnPoints = new ArrayList<>();

        for (float t = 0; t <= 1; t += intensity){
            PointF pointF = new PointF(deCasteljauX(order, 0, t), deCasteljauY(order, 0, t));
            returnPoints.add(pointF);
        }

        return returnPoints;
    }

    /**
     *  p(i,j) =  (1-u) * p(i-1,j)  +  u * p(i-1,j-1)
     *
     * de Casteljau递归算法 获取最终的贝塞尔曲线x坐标
     *
     * @param order 阶数
     * @param index 坐标点数组下标
     * @param ratio [0 - 1] 这个为每个线段中，取点的比值
     * @return
     */
    public float deCasteljauX(int order, int index, float ratio) {
        if (order == 1) {
            return (1 - ratio) * computePoints.get(index).x + ratio * computePoints.get(index + 1).x;
        }

        //解阶
        return (1 - ratio) * deCasteljauX(order - 1, index, ratio) + ratio * deCasteljauX(order - 1, index + 1, ratio);
    }

    /**
     *  p(i,j) =  (1-u) * p(i-1,j)  +  u * p(i-1,j-1)
     *
     * de Casteljau递归算法 获取最终的贝塞尔曲线y坐标
     *
     * @param order 阶数
     * @param index 坐标点数组下标
     * @param ratio [0 - 1] 这个为每个线段中，取点的比值
     * @return
     */
    public float deCasteljauY(int order, int index, float ratio) {
        if (order == 1) {
            return (1 - ratio) * computePoints.get(index).y + ratio * computePoints.get(index + 1).y;
        }

        //解阶
        return (1 - ratio) * deCasteljauY(order - 1, index, ratio) + ratio * deCasteljauY(order - 1, index + 1, ratio);
    }
}
