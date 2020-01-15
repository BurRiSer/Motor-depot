package controller.order.request;

import controller.Action;
import controller.ActionResult;
import domain.Order;
import domain.Vehicle;
import service.OrderService;
import service.ServiceException;
import service.VehicleService;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

    /*выбор соответствующего заявке автомобиля по типу и состоянию*/
public class RequestConfirmAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService orderService = getServiceFactory().getOrderService();
            Order order = orderService.findById(Long.parseLong(req.getParameter("id")));
            VehicleService vehicleService = getServiceFactory().getVehicleService();
            List<Vehicle> vehicles  = vehicleService.findByTypeAndServiceability(order.getVehicleType(), true);
            req.setAttribute("order", order);
            req.setAttribute("vehicles", vehicles);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
