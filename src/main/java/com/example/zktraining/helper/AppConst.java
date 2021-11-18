package com.example.zktraining.helper;

public class AppConst {

    public static class REDIRECT {
        public static final String LOGIN = "/login";
        public static final String INDEX = "/index";
    }

    public static class Auth {
        public static final String AUTH_KEY = "EMS_HTTP_SESSION_AUTHORIZED"; // sessionId - expired time
        public static final String INFO_USER = "INFORMATION_USER";
    }
    public static class STRING {
        public static final String EMPTY = "";
        public static final String DFTHEME = "theme1 z-hlayout z-flex z-flex-row";
        public static final String DFLANG = "en_US";
        public static final String FILTER = "filter";
        public static final String GROUPBY = "groupBy";
        public static final String TACKED = "tacked sidebar";
        public static final String UNTACKED = "untacked sidebar";
        public static final String WIDGETS = "widgets";
        public static final String QUERYS = "querys";
    }
    public static class Language {
        public final static String ACTIVE_LANG = "active.lang";
        public final static String ACTIVE_LANG_IMAGE = "active.lang.image";
        public static final String IMAGE_RESOURCES = "/resources/images/flags/4x3/";
        public static final String VN = "vn";
        public static final String US = "us";
    }

    public static final String NAVIGATION = "navigation";
    public static final String TABBOXES = "tabboxes";
}
