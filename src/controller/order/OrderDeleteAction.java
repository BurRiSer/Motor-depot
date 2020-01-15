package controller.order;

import controller.Action;
import controller.ActionResult;
import factory.FactoryException;
import service.OrderService;
import service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    /*удаление заявки*/
public class OrderDeleteAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if(id != null) {
            try {
                OrderService service = getServiceFactory().getOrderService();
                service.delete(id);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new ActionResult("/order/requests.html");
    }
}
