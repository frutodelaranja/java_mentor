package servlet;

import com.google.gson.Gson;
import model.Car;
import model.DailyReport;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(200);
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        String json;

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        json = gson.toJson(buyCar(new Car(brand, model, licensePlate)));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(200);
        resp.getWriter().println(json);
    }

    public Car buyCar(Car c) {
        if (DailyReportService.getInstance().isEmpty()) {
            DailyReportService.getInstance().addDailyReport();
        }

        List<Car> carsArePresent = CarService.getInstance().getAllCars();
        Long earnings;
        Long soldCars;

        Car buyCar = new Car();
        for (Car car :
                carsArePresent) {
            if (car.equals(c)) {
                buyCar = car;

            }
        }

        earnings = buyCar.getPrice() + DailyReportService.getInstance().getLastReport().getEarnings();
        soldCars = DailyReportService.getInstance().getLastReport().getSoldCars() + 1;

        CarService.getInstance().deleteCar(buyCar);
        DailyReportService.getInstance().updateDailyReport(earnings, soldCars);
        return buyCar;
    }
}
