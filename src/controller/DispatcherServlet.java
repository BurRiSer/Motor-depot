package controller;

import factory.ServiceFactory;
import factory.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.ApplicationStartListener.logger;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ServletException e) {
            logger.error("ServletException in GET method", e);
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ServletException e) {
            logger.error("ServletException in POST method", e);
            throw e;
        }
    }

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = new UrlRequestHandler(req).getUrl();
        String context = req.getContextPath();
        Action action = ActionFactory.getAction(url);
        ActionResult actionResult = null;
        if (action != null) {
            try (ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                actionResult = action.execute(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        if (actionResult != null && actionResult.isRedirect()) {
            resp.sendRedirect(context + actionResult.getUrl());
        } else {
            if (actionResult != null && actionResult.getUrl() != null) {
                url = actionResult.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
