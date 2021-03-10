package indi.rennnhong.staterkit.util;

import java.text.MessageFormat;

public class ThymeleafPathUtils {
    public static final String ROOT_PATH = "module";

    public enum FormType {
        CREATE, DETAIL, UPDATE, DELETED
    }

    public static String buildPath(String modulePath) {
        return MessageFormat.format("{0}/{1}", ROOT_PATH, modulePath);
    }

    public static String buildViewPath(String modulePath, String path) {
        String txtTemplate = "{0}/{1}";
        return MessageFormat.format(txtTemplate, buildPath(modulePath), path);
    }

    public static String buildViewPath(String modulePath, FormType formType) {
        String txtTemplate = "{0}_form_{1}";
        switch (formType) {
            case CREATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), "create");
            case UPDATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), "update");
            case DETAIL:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), "detail");
            case DELETED:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), "deleted");
        }
        return "";
    }

    public static String buildFragmentPath(String modulePath, String path) {
        String txtTemplate = "{0} :: {1}";
        return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), path);

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
            case DELETED:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, formType), "deleted_form");
        }
        return "";
    }

    public static void main(String[] args) {
        String modulePath = "student";

        System.out.println(buildPath(modulePath));
        System.out.println(buildViewPath(modulePath,"my-view"));

        System.out.println(buildViewPath(modulePath,FormType.CREATE));
        System.out.println(buildViewPath(modulePath,FormType.UPDATE));
        System.out.println(buildViewPath(modulePath,FormType.DETAIL));
        System.out.println(buildViewPath(modulePath,FormType.DELETED));

        System.out.println(buildFragmentPath(modulePath,"my-fragment"));

        System.out.println(buildFragmentPath(modulePath,FormType.CREATE));
        System.out.println(buildFragmentPath(modulePath,FormType.UPDATE));
        System.out.println(buildFragmentPath(modulePath,FormType.DETAIL));
        System.out.println(buildFragmentPath(modulePath,FormType.DELETED));
    }

}
