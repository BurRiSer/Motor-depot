package controller.order;

import controller.Action;
import controller.ActionResult;
import domain.Order;
import domain.OrderStatus;
import domain.User;
import domain.VehicleType;
import service.OrderService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static controller.ApplicationStartListener.logger;

    /*создание/редактирование заявки*/
public class OrderSaveAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        try {
            order.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
            logger.error(e.toString(), e);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            order.setDate(simpleDateFormat.parse(req.getParameter("date")));
        } catch (ParseException e) {
        }
        order.setDeparturePoint(req.getParameter("departure"));
        order.setArrivalPoint(req.getParameter("arrival"));
        try {
            order.setVehicleType(VehicleType.values()[Integer.parseInt(req.getParameter("type"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        order.setStatus(OrderStatus.NOT_SERVED);
        order.setUser(new User());
        order.getUser().setId(Long.parseLong(req.getParameter("user_id")));
            try {
                OrderService service = getServiceFactory().getOrderService();
                service.save(order);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        return new ActionResult("/order/requests.html");
    }
}
