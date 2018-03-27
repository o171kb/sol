package tt.com.utils;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * DESC : 차트관련 클래스<br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오후 1:10:21
 */
public class UtCoChartUtil {

    private static final int DEFAULT_CHART_WIDTH = 400;
    private static final int DEFAULT_CHART_HEIGHT = 350;

    public static void main(String[] arg) {
        UtCoChartUtil bcb = new UtCoChartUtil();
        JFreeChart chart = bcb.getBarChart();
        ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
        frame1.setSize(DEFAULT_CHART_WIDTH, DEFAULT_CHART_HEIGHT);
        frame1.setVisible(true);
    }

    public JFreeChart getBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(2, "Marks", "Rahul");
        dataset.setValue(7, "Marks", "Vinod");
        dataset.setValue(4, "Marks", "Deepak");
        dataset.setValue(9, "Marks", "Prashant");
        dataset.setValue(6, "Marks", "Chandan");
        JFreeChart chart = ChartFactory.createBarChart("BarChart using JFreeChart", "Student", "Marks", dataset,
                PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.yellow);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        return chart;
    }
}