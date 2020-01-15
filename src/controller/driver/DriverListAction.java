package controller.driver;

import controller.Action;
import controller.ActionResult;
import domain.Driver;
import service.DriverService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

    /*список водителей*/
public class DriverListAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DriverService service = getServiceFactory().getDriverService();
            List<Driver> drivers  = service.findAll();
            req.setAttribute("drivers", drivers);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
