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

public class UserEditAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);
                boolean userCanBeDeleted = service.canDelete(id);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        return null;
    }
}
