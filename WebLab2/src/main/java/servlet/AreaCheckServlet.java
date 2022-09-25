package servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Vector;
import java.util.stream.DoubleStream;


public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Instant start = Instant.now();
        long startTime = System.nanoTime();
        double x = Double.parseDouble(req.getParameter("x_value").replace(',', '.'));
        double y = Double.parseDouble(req.getParameter("y_value").replace(',', '.'));
        double r = Double.parseDouble(req.getParameter("r_value").replace(',', '.'));
        if (!checkArea(x, y, r)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            boolean correctCoordinate = false;

            if ((x >= -r / 2 && x <= 0 && y >= -r && y <= 0)
                    || (r>=x-y && y <= 0 && x >= 0)
                    || ((x * x + y * y) <= r * r / 4 && x <= 0 && y >= 0)) {
                correctCoordinate = true;
            }

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                            .withLocale(Locale.UK)
                            .withZone(ZoneId.systemDefault());
            String time = formatter.format(start);
            long elatedTime = System.nanoTime() - startTime;
            Cordinate coordinate = new Cordinate(x, y, r, time, elatedTime, correctCoordinate);
            ServletContext context = req.getSession().getServletContext();
            Object attribute = context.getAttribute("userData");
            Vector<Cordinate> coordinatesCollection = new Vector<>();
            if (!(attribute == null || ((Vector<Cordinate>) attribute).size() == 0)) {
                coordinatesCollection = (Vector<Cordinate>) attribute;
            }
            coordinatesCollection.add(coordinate);
            context.setAttribute("userData", coordinatesCollection);

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            StringBuilder tableRow = new StringBuilder();
            for (Cordinate element : coordinatesCollection) {
                tableRow.append("<div class=\"table-row\">\n")
                        .append("<div class=\"table-data\">").append(element.getX()).append("</div>\n")
                        .append("<div class=\"table-data\">").append(element.getY()).append("</div>\n")
                        .append("<div class=\"table-data\">").append(element.getR()).append("</div>\n")
                        .append("<div class=\"table-data\">").append(element.getCorrectWords()).append("</div>\n")
                        .append("<div class=\"table-data\">").append(element.getRequestTime()).append("</div>\n")
                        .append("<div class=\"table-data\">").append(element.getExecutionTime()).append("</div>\n")
                        .append("</div>");
            }
            writer.println(tableRow);
        }
    }

    private boolean checkArea(Double x, Double y, Double r) {
        double[] a = { 1.0, 2.0, 3.0, 4.0};
        return x >= -3.0 && x <= 5.0 && y >= -3.0 && y <= 5.0 && DoubleStream.of(a).anyMatch(rad -> rad == r);
    }
}