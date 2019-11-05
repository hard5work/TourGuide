package com.lintend.tourguide.sessionManager;

public class Constant {
    public static String IMG_PATH_CATEGORY = "uploads/category";
    public static String IMG_PATH_RECIPE = "uploads/recipe";
    public static final int LIMIT_CATEGORY_REQUEST = 100;
    public static final int LIMIT_LOADMORE = 100;
    public static final int LIMIT_RECIPE_REQUEST = 100;
    public static final String LOG_TAG =" RECIPE_LOG";
    public static final String PROJECT_API_NUMBER = "2309XXXXXXXXX";
    public static String WEB_URL = "http://sirseni.com/android/travel-nepal/";

    public enum Event{
        FAVORITES,
        THEME,
        NOTIFICATION,
        REFRESH
    }

    public static String getURLimgRecipe(String file_name){
        return WEB_URL + IMG_PATH_RECIPE + "/" + file_name;
    }

    public static String getURLimgCategory(String file_name){
        return WEB_URL + IMG_PATH_CATEGORY + "/" + file_name;
    }
}
