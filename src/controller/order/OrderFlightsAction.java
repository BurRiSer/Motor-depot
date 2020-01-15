package controller.order;

import controller.Action;
import controller.ActionResult;
import domain.*;
import service.DriverService;
import service.OrderService;
import service.ServiceException;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

    /*список рейсов для водителя*/
public class OrderFlightsAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                try {
                    OrderService service = getServiceFactory().getOrderService();
                    List<Order> orders;
                    DriverService driverService = getServiceFactory().getDriverService();
                    Driver driver;
                    driver = driverService.findByUserId(user.getId());
                    orders = service.findByStatusAndDriverId(OrderStatus.IN_PROCESS, driver.getId());
                    req.setAttribute("orders", orders);
                } catch (FactoryException | ServiceException e) {
                    throw new ServletException(e);

                }
            } else {
                return new ActionResult("/login.html?message=login.message.access.denied");
            }
        }
        return null;
    }
}