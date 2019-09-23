package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("all")) {
            Gson gson = new Gson();
            String json = gson.toJson(DailyReportService.getInstance().getAllDailyReports());
            resp.setStatus(200);
            resp.getWriter().write(json);
        } else if (req.getPathInfo().contains("last")) {
            Gson gson = new Gson();
            String json = gson.toJson(DailyReportService.getInstance().getLastReport());
            resp.setStatus(200);
            resp.getWriter().write(json);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DailyReportService.getInstance().deleteAllReports();
        CarService.getInstance().deleteAllCar();
    }
}
