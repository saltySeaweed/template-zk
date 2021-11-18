package com.example.zktraining.helper;

import org.zkoss.zk.ui.Sessions;

import java.util.List;
import java.util.Locale;

public class Utils {
    public static void refreshLang(String language) {
        String lg;
        Locale prefer_locale;
        if (language.length() > 2) {
            lg = language.substring(0, 2);
            prefer_locale = new Locale(lg, language.substring(3));
        } else {
            lg = language;
            prefer_locale = new Locale(lg);
        }
        Sessions.getCurrent().setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, prefer_locale);
        Sessions.getCurrent().setAttribute(AppConst.Language.ACTIVE_LANG, language);
        Sessions.getCurrent().setAttribute(AppConst.Language.ACTIVE_LANG_IMAGE, lg);
    }
    public static boolean contains(final List<Integer> arr, final Integer key) {
        return arr.stream().mapToInt(Integer::intValue).anyMatch(i -> i == key);
    }
}
