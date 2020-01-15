package controller;

public class ActionResult {
    private String url;
    private boolean redirect;

    public ActionResult(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public ActionResult(String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return redirect;
    }
}