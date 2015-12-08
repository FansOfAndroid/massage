package com.dfsebook.mssage.util;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by Administrator on 15-11-22.
 */
public class PieChart {

    public static GraphicalView getPie(Context context) {
        int[] colors={Color.BLUE,Color.GREEN,Color.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        double[] values={3,6,2};
        CategorySeries dataset = buildCategoryDataset("测试饼图", values);
        GraphicalView graphicalView = ChartFactory.getPieChartView(context, dataset, renderer);
        return graphicalView;
    }

    private static CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("已预约", values[0]);
        series.add("剩余", values[1]);
        series.add("排队", values[2]);
        return series;
    }

    private static DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setBackgroundColor(Color.TRANSPARENT);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsTextSize(12);

        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);

        }
        return renderer;
    }

}
