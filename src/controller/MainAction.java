package controller;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainAction extends Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMINISTRATOR:
                        return new ActionResult("/user/list.html");
                    case DRIVER:
                    case DISPATCHER:
                    case USER:
                        return new ActionResult("/order/requests.html");
                    default:
                        return new ActionResult("/login.html");
                }
            } else {
                return new ActionResult("/login.html");
            }
        } else {
            return new ActionResult("/login.html");
        }
    }
}