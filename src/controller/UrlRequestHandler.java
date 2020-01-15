package controller;

import javax.servlet.http.HttpServletRequest;

public class UrlRequestHandler {
    private HttpServletRequest request;
    private String url;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public UrlRequestHandler(HttpServletRequest request) {
        this.request = request;
        execute();
    }

    /*взять путь без расширения*/
    public void execute() {
        url = request.getRequestURI();
        String context = request.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
    }
}
