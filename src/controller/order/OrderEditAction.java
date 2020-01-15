package controller.order;

import controller.Action;
import controller.ActionResult;
import domain.Order;
import domain.VehicleType;
import service.OrderService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    /*редактирование заявки пользователем*/
public class OrderEditAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                OrderService service = getServiceFactory().getOrderService();
                Order order = service.findById(id);
                req.setAttribute("order", order);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("types", VehicleType.values());
        return null;
    }
}
