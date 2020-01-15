package controller.user;

import controller.Action;
import controller.ActionResult;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.UserService;
import factory.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        user.setLogin(req.getParameter("login"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        if (user.getLogin() != null && user.getRole() != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new ActionResult("/user/list.html");
    }
}
