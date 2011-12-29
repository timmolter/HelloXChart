package com.xeiam.helloxchart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xeiam.xcharts.BitmapEncoder;
import com.xeiam.xcharts.Chart;
import com.xeiam.xcharts.QuickChart;

@javax.servlet.annotation.WebServlet(urlPatterns = { "/chart" })
public class ChartServlet extends HttpServlet {

    private static Map<String, Chart> CHART_MAP = new HashMap<String, Chart>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // generate the Chart
        String action = request.getParameter("action");
        if (action != null && !action.equals("null")) {
            String chartId = generateRandomChart();
            request.setAttribute("chart_id", chartId);
            request.getRequestDispatcher("/hello.jsp").forward(request, response);
        }

        // Fetch the Chart
        String chartId = request.getParameter("chart_id");
        if (chartId != null && !chartId.equals("null")) {

            Chart chart = CHART_MAP.get(chartId);
            if (chart != null) {
                response.setContentType("image/png");
                ServletOutputStream out = response.getOutputStream();
                try {
                    BitmapEncoder.streamPNG(out, chart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.close();
                chart = null;
                CHART_MAP.remove(chartId);
            } else {
                System.err.println("CHART NOT FOUND!!!");
            }
        }
    }

    // generate the chart
    public static String generateRandomChart() {

        Chart chart = QuickChart.getChart("XChart Sample - Random Walk", "X", "Y", null, null, getRandomWalk(105));
        String uuid = UUID.randomUUID().toString();
        CHART_MAP.put(uuid, chart);
        return uuid;
    }

    // generate random walk data set
    private static double[] getRandomWalk(int numPoints) {

        double[] y = new double[numPoints];
        y[0] = 0;
        for (int i = 1; i < y.length; i++) {
            y[i] = y[i - 1] + Math.random() - .5;
        }
        return y;
    }
}
