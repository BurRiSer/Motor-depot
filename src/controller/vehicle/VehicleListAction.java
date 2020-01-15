package controller.vehicle;

import controller.Action;
import controller.ActionResult;
import domain.Vehicle;
import service.ServiceException;
import service.VehicleService;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VehicleListAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            VehicleService service = getServiceFactory().getVehicleService();
            List<Vehicle> vehicles  = service.findAll();
            req.setAttribute("vehicles", vehicles);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
