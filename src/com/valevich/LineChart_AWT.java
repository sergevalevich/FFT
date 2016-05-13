package com.valevich;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChart_AWT extends ApplicationFrame {
    private static final int N = 32;
    private static final Complex[] x = new Complex[N];
    public LineChart_AWT(String applicationTitle, String chartTitle, DefaultCategoryDataset dataset) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "", "",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private static DefaultCategoryDataset createStartDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int N = 32;

        for (int i = 0; i < N; i++) {
            double a = -2 * Math.random() + 1;
            x[i] = new Complex(a,0);
            dataset.addValue(a, "x", new Integer(i));
        }

        FFT.show(x, "x");

        return dataset;
    }

    private static DefaultCategoryDataset createFFTDataset() {
        Complex[] y = FFT.fft(x);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i<N; i++) {
            dataset.addValue(y[i].abs(),"y",new Integer(i));
        }
        FFT.show(y, "y = fft(x)");
        return dataset;
    }

    public static void main(String[] args) {


        LineChart_AWT startChart = new LineChart_AWT(
                "",
                "", createStartDataset());
        LineChart_AWT fftChart = new LineChart_AWT(
                "",
                "", createFFTDataset());

        startChart.pack();
        fftChart.pack();

        RefineryUtilities.centerFrameOnScreen(startChart);
        RefineryUtilities.centerFrameOnScreen(fftChart);

        startChart.setVisible(true);
        fftChart.setVisible(true);
    }
}