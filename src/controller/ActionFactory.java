package controller;

import controller.driver.DriverListAction;
import controller.driver.DriverViewAction;
import controller.login.LoginAction;
import controller.login.LogoutAction;
import controller.order.*;
import controller.order.request.RequestConfirmAction;
import controller.order.request.RequestSaveAction;
import controller.user.password.PasswordResetAction;
import controller.user.password.PasswordSaveAction;
import controller.user.UserDeleteAction;
import controller.user.UserEditAction;
import controller.user.UserListAction;
import controller.user.UserSaveAction;
import controller.vehicle.VehicleEditAction;
import controller.vehicle.VehicleListAction;
import controller.vehicle.VehicleSaveAction;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/password/save", PasswordSaveAction.class);
        actions.put("/password/reset", PasswordResetAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/vehicle/list", VehicleListAction.class);
        actions.put("/vehicle/edit", VehicleEditAction.class);
        actions.put("/vehicle/serviceability", VehicleEditAction.class);
        actions.put("/vehicle/save", VehicleSaveAction.class);

        actions.put("/driver/list", DriverListAction.class);
        actions.put("/driver/view", DriverViewAction.class);

        actions.put("/order/requests", OrderRequestsAction.class);
        actions.put("/order/edit", OrderEditAction.class);
        actions.put("/order/save", OrderSaveAction.class);
        actions.put("/order/delete", OrderDeleteAction.class);
        actions.put("/order/confirm", RequestConfirmAction.class);
        actions.put("/order/request/save", RequestSaveAction.class);
        actions.put("/order/start", OrderStartAction.class);
        actions.put("/order/flights", OrderFlightsAction.class);
        actions.put("/order/flight/complete", OrderCompleteAction.class);

    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if (action != null) {
            try {
                return (Action) action.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NullPointerException |
                    NoSuchMethodException | InvocationTargetException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
