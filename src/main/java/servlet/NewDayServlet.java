package servlet;

import com.google.gson.Gson;
import service.DailyReportService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewDayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DailyReportService dailyReportService = DailyReportService.getInstance();
        dailyReportService.createNewDayReport();
        Gson gson = new Gson();
        String json = gson.toJson(dailyReportService.getLastReport());
        resp.getWriter().write(json);
        resp.setStatus(200);

    }
}
