package controller.vehicle;

import controller.Action;
import controller.ActionResult;
import domain.Vehicle;
import domain.VehicleType;
import service.ServiceException;
import service.VehicleService;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VehicleSaveAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = new Vehicle();
        try {
            vehicle.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        vehicle.setModel(req.getParameter("model"));
        try {
            vehicle.setType(Enum.valueOf(VehicleType.class, req.getParameter("type")));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        vehicle.setServiceability(Boolean.parseBoolean(req.getParameter("serviceability")));
        if (vehicle.getModel() != null && vehicle.getType() != null) {
            try {
                VehicleService service = getServiceFactory().getVehicleService();
                service.save(vehicle);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new ActionResult("/vehicle/list.html");
    }
}
