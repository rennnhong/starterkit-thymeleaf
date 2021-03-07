package indi.rennnhong.staterkit.util;

import sun.plugin2.message.Message;

import java.text.MessageFormat;

public class ThymeleafPathUtils {
    public static final String ROOT_PATH = "module";

    public enum FormType {
        CREATE, DETAIL, UPDATE
    }

    public static String buildViewPath(String modulePath, FormType formType) {
        String txtTemplate = "{0}/{1}_form_{2}";
        switch (formType) {
            case CREATE:
                return MessageFormat.format(txtTemplate, buildPath(modulePath), modulePath, "create");
            case UPDATE:
                return MessageFormat.format(txtTemplate, buildPath(modulePath), modulePath, "update");
            case DETAIL:
                return MessageFormat.format(txtTemplate, buildPath(modulePath), modulePath, "detail");

        }
        return "";
    }

    public static String buildFragmentPath(String modulePath, FormType formType) {
        String txtTemplate = "{0} :: {1}";
        switch (formType) {
            case CREATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, formType), "create_form");
            case UPDATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, formType), "update_form");
            case DETAIL:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, formType), "detail_form");

        }
        return "";
    }

    public static String buildPath(String modulePath) {
        return MessageFormat.format("{0}/{1}", ROOT_PATH, modulePath);
    }
}
