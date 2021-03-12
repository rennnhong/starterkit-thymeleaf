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

    public static String buildViewPath(String modulePath, String path, FormType formType) {
        String txtTemplate = "{0}_form_{1}";
        switch (formType) {
            case CREATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path), "create");
            case UPDATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path), "update");
            case DETAIL:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path), "detail");
            case DELETED:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path), "deleted");
        }
        return "";
    }

    public static String buildFragmentPath(String modulePath, String path) {
        String txtTemplate = "{0} :: {1}";
        return MessageFormat.format(txtTemplate, buildViewPath(modulePath, modulePath), path);

    }

    public static String buildFragmentPath(String modulePath, String path, FormType formType) {
        String txtTemplate = "{0} :: {1}";
        switch (formType) {
            case CREATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path, formType), "create_form");
            case UPDATE:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path, formType), "update_form");
            case DETAIL:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path, formType), "detail_form");
            case DELETED:
                return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path, formType), "deleted_form");
        }
        return "";
    }

    public static void main(String[] args) {
        String modulePath = "student";
        String subPath = "lev1/lev2";

        System.out.println(buildPath(modulePath));
        System.out.println(buildViewPath(modulePath, subPath));

        System.out.println(buildViewPath(modulePath, subPath, FormType.CREATE));
        System.out.println(buildViewPath(modulePath, subPath, FormType.UPDATE));
        System.out.println(buildViewPath(modulePath, subPath, FormType.DETAIL));
        System.out.println(buildViewPath(modulePath, subPath, FormType.DELETED));

        System.out.println(buildFragmentPath(modulePath, subPath));

        System.out.println(buildFragmentPath(modulePath, subPath,FormType.CREATE));
        System.out.println(buildFragmentPath(modulePath, subPath,FormType.UPDATE));
        System.out.println(buildFragmentPath(modulePath, subPath,FormType.DETAIL));
        System.out.println(buildFragmentPath(modulePath, subPath,FormType.DELETED));
    }

}
