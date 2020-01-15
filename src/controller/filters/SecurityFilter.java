package controller.filters;

import controller.UrlRequestHandler;
import domain.Role;
import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();
    static {
        Set<Role> all = new HashSet<>(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMINISTRATOR);
        Set<Role> carEditors = new HashSet<>();
        carEditors.add(Role.ADMINISTRATOR);
        carEditors.add(Role.DRIVER);
        Set<Role> employee = new HashSet<>();
        employee.add(Role.ADMINISTRATOR);
        employee.add(Role.DISPATCHER);
        employee.add(Role.DRIVER);
        Set<Role> driver = new HashSet<>();
        driver.add(Role.DRIVER);
        Set<Role> dispatcher = new HashSet<>();
        dispatcher.add(Role.DISPATCHER);

        permissions.put("/logout", all);
        permissions.put("/password/edit", all);
        permissions.put("/password/save", all);
        permissions.put("/password/reset", admin);

        permissions.put("/user/list", admin);
        permissions.put("/user/edit", admin);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/vehicle/list", employee);
        permissions.put("/vehicle/edit", admin);
        permissions.put("/vehicle/serviceability", driver);
        permissions.put("/vehicle/save", carEditors);

        permissions.put("/driver/list", employee);
        permissions.put("/driver/view", driver);

        permissions.put("/order/requests", all);
        permissions.put("/order/edit", all);
        permissions.put("/order/save", all);
        permissions.put("/order/confirm", dispatcher);
        permissions.put("/order/request/save", dispatcher);
        permissions.put("/order/start", driver);
        permissions.put("/order/flights", driver);
        permissions.put("/order/flight/complete", driver);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = new UrlRequestHandler(httpReq).getUrl();
        String context = httpReq.getContextPath();
        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                User user = (User)session.getAttribute("currentUser");
                if(user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() {}
}
