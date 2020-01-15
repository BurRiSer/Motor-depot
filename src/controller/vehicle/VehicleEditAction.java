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

public class VehicleEditAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                VehicleService service = getServiceFactory().getVehicleService();
                Vehicle vehicle = service.findById(id);
                req.setAttribute("vehicle", vehicle);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
