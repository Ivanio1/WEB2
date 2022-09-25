package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;


public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xString = request.getParameter("x_value");
        String yString = request.getParameter("y_value");
        String rString = request.getParameter("r_value");
        if (xString == null || yString == null || rString == null || isNotNumeric(xString.replace(',', '.'))
                || isNotNumeric(yString.replace(',', '.')) || isNotNumeric(rString.replace(',', '.'))) {
            if (request.getParameter("type").equals("clear")) {
                ServletContext context = getServletContext();
                Object attribute = context.getAttribute("userData");
                if (!(attribute == null || ((Vector<Cordinate>) attribute).size() == 0)) {
                    context.setAttribute("userData", new Vector<Cordinate>());
                }
            } else{
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);}
        } else {
            request.getServletContext().getRequestDispatcher("/checking").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private static boolean isNotNumeric(String str){
        return !str.matches("-?\\d+(\\.\\d+)?");
    }
}