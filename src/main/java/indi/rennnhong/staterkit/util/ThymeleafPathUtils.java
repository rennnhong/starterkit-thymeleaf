package indi.rennnhong.staterkit.util;

import java.text.MessageFormat;

public class ThymeleafPathUtils {
    public static final String ROOT_PATH = "module";

    public static String buildPath(String modulePath) {
        return MessageFormat.format("{0}/{1}", ROOT_PATH, modulePath);
    }

    public static String buildViewPath(String modulePath, String path) {
        String txtTemplate = "{0}/{1}";
        return MessageFormat.format(txtTemplate, buildPath(modulePath), path);
    }

    public static String buildFragmentPath(String modulePath, String path, String fragmentName) {
        String txtTemplate = "{0} :: {1}";
        return MessageFormat.format(txtTemplate, buildViewPath(modulePath, path), fragmentName);

    }


    public static void main(String[] args) {
        String modulePath = "student";
        String subPath = "lev1/lev2";

        System.out.println(buildPath(modulePath));
        System.out.println(buildViewPath(modulePath, subPath));
        System.out.println(buildFragmentPath(modulePath, subPath, "custom_fragment"));

    }

}
