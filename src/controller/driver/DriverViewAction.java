package controller.driver;

import controller.Action;
import controller.ActionResult;
import domain.Driver;
import domain.User;
import domain.Vehicle;
import service.DriverService;
import service.ServiceException;
import service.VehicleService;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

    /*личный кабинет водителя*/
public class DriverViewAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                try {
                    DriverService driverService = getServiceFactory().getDriverService();
                    Driver driver = driverService.findByUserId(user.getId());
                    VehicleService vehicleService = getServiceFactory().getVehicleService();
                    Vehicle vehicle  = vehicleService.findById(driver.getVehicle().getId());
                    req.setAttribute("driver", driver);
                    req.setAttribute("vehicle", vehicle);
                } catch (FactoryException | ServiceException e) {
                    throw new ServletException(e);

                }
            } else {
                return new ActionResult("/login.html?message=login.message.access.denied");
            }
        }
        return null;
    }
}
