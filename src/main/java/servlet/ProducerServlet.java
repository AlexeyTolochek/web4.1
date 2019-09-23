package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(403);
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        String priceStr = req.getParameter("price");
        if (!brand.equals("") && !model.equals("") && !licensePlate.equals("") && !priceStr.equals("")) {
            Long price = Long.parseLong(priceStr);
            CarService service = CarService.getInstance();
            if (service.getNumberBrand(brand) < 10) {
                Car car = new Car(brand, model, licensePlate, price);
                service.addCar(car);
                resp.setStatus(200);
            }
        }
    }
}
