package controller.order.request;

import controller.Action;
import controller.ActionResult;
import domain.Driver;
import domain.OrderStatus;
import service.DriverService;
import service.OrderService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    /*подтверждение заявки диспетчером для водителя*/
public class RequestSaveAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
            }
            DriverService driverService = getServiceFactory().getDriverService();
            Driver driver = driverService.findByVehicleId(Long.parseLong(req.getParameter("vehicle_id")));
            if (driver != null) {
                OrderService service = getServiceFactory().getOrderService();
                service.confirmDriver(id, driver);
                service.changeStatus(id, OrderStatus.ACCEPTED);
            } else {
                return new ActionResult("/order/confirm.html?error");
            }
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new ActionResult("/index.html");
    }
}
