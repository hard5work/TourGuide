package com.lintend.tourguide.sessionManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;

import com.lintend.tourguide.model.Modules;
import com.google.gson.Gson;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Modules modules;
    private Context context;
    public static final String PREF_NAME = "name";
    int PRIVATE_MODE = 0;
    public static final String KEY_FAVORITE = "favorite";

    public static final String KEY_FAVORITE_NAME = "name";
    public static final String KEY_FAVORITE_IMAGE = "image";
    public static final String IS_FAV = "isfav";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
       this.context = context;
       preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
       editor = preferences.edit();
    }

/*
    */
/*String place id  */
/*


    public void saveFavorites( List<String> favorites) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String b: favorites){
            stringBuilder.append(b);
            stringBuilder.append(",");
        }
        editor.putString(KEY_FAVORITE, stringBuilder.toString());
        editor.commit();
    }

    public void addFavorite(String id) {
        List<String> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(id);
        saveFavorites(favorites);
    }


    public void removeFavorite(String id) {
        ArrayList<String> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(id);
            saveFavorites(favorites);
        }
    }

    public ArrayList<String> getFavorites() {

        String wordString = preferences.getString(KEY_FAVORITE, null);
        assert wordString != null;
        String[] itemWords =   wordString.split(",");
        List<String> items = new ArrayList<String>(Arrays.asList(itemWords));

        return (ArrayList<String>) items;

    }

    ////for name

    public void savenameFavorites( List<String> favorites) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String b: favorites){
            stringBuilder.append(b);
            stringBuilder.append(",");
        }
        editor.putString(KEY_FAVORITE_NAME, stringBuilder.toString());
        editor.commit();
    }

    public void addnameFavorite(String name) {
        List<String> favorites = getnameFavorites();
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(name);
        saveFavorites(favorites);
    }


    public void removenameFavorite(String name) {
        ArrayList<String> favorites = getnameFavorites();
        if (favorites != null) {
            favorites.remove(name);
            saveFavorites(favorites);
        }
    }

    public ArrayList<String> getnameFavorites() {

        String wordString = preferences.getString(KEY_FAVORITE_NAME, null);
        assert wordString != null;
        String[] itemWords =   wordString.split(",");
        List<String> items = new ArrayList<String>(Arrays.asList(itemWords));

        return (ArrayList<String>) items;

    }

    /////////// images
    public void saveimageFavorites( List<String> favorites) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String b: favorites){
            stringBuilder.append(b);
            stringBuilder.append(",");
        }
        editor.putString(KEY_FAVORITE_IMAGE, stringBuilder.toString());
        editor.commit();
    }

    public void addimageFavorite(String image) {
        List<String> favorites = getimageFavorites();
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(image);
        saveFavorites(favorites);
    }


    public void removeimageFavorite(String image) {
        ArrayList<String> favorites = getimageFavorites();
        if (favorites != null) {
            favorites.remove(image);
            saveFavorites(favorites);
        }
    }

    public ArrayList<String> getimageFavorites() {

        String wordString = preferences.getString(KEY_FAVORITE_IMAGE, null);
        assert wordString != null;
        String[] itemWords =   wordString.split(",");
        List<String> items = new ArrayList<String>(Arrays.asList(itemWords));

        return (ArrayList<String>) items;

    }*/





    public void saveFavorites(List<Modules> favorites) {

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);



/* StringBuilder stringBuilder = new StringBuilder();
        for(Modules b: favorites){
            stringBuilder.append(b);
            stringBuilder.append(",");
        }*/



        editor.putString(KEY_FAVORITE, jsonFavorites);

        editor.commit();
    }

    public void addFavorite( Modules product) {
        List<Modules> favorites = getFavorites();
        if (favorites == null)
            favorites = new ArrayList<Modules>();
        favorites.add(product);
        saveFavorites(favorites);
    }

    public void removeFavorite(Modules product) {
        List<Modules> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(favorites);
        }
    }

    public List<Modules> getFavorites() {
        List<Modules> favorites;



/*

        String wordString = preferences.getString(KEY_FAVORITE, null);
        assert wordString != null;
        String[] itemWords =  wordString.split(",");
        List<Modules> items = new ArrayList<Modules>(Arrays.<Modules>asList(itemWords);

        return (ArrayList<Modules>) items;
*/



            String jsonFavorites = preferences.getString(KEY_FAVORITE, null);
            Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonFavorites));
        reader.setLenient(true);
            Modules favorite =  gson.fromJson(String.valueOf(reader), Modules.class);

            favorites = Arrays.asList(favorite);
            favorites = new ArrayList<Modules>(favorites);

        return favorites;
    }




}

