package controller.order;

import controller.Action;
import controller.ActionResult;
import domain.OrderStatus;
import service.OrderService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    /*завершение рейса водителем*/
public class OrderCompleteAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
            }
            OrderService orderService = getServiceFactory().getOrderService();
            orderService.changeStatus(id, OrderStatus.COMPLETED);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new ActionResult("/order/flights.html");
    }
}
