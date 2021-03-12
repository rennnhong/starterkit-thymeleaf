package indi.rennnhong.staterkit.util;

import java.text.MessageFormat;

public class RouteUtils {
    public enum Route {
        CREATE_SUCCESS, UPDATE_SUCCESS
    }

    public static String forward(String path, Route route) {
        if (path.startsWith("/")) path = path.substring(1);
        String txtTemplate = "forward:/{0}";
        if (route == Route.CREATE_SUCCESS || route == Route.UPDATE_SUCCESS)
            return MessageFormat.format(txtTemplate, MessageFormat.format("{0}/result/success", path));
        return MessageFormat.format(txtTemplate, path);
    }
}
