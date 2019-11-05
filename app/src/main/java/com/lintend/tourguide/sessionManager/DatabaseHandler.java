package com.lintend.tourguide.sessionManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lintend.tourguide.model.Modules;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    private static final String C_BANNER = "banner";
    private static final String C_DESCRIPTION = "description";
    private static final String C_ID = "id";
    private static final String C_NAME = "name";
    private static final String C_RECIPES = "recipes";
    private static final String DATABASE_NAME = "m_favlist";
    private static final int DATABASE_VERSION = 2;
    private static final String R_CATEGORY="category";
    private static final String R_CATEGORY_NAME = "category_name";
    private static final String R_DATE_CREATE = "date_created";
    private static final String R_DURATION = "duration";
    private static final String R_ID = "id";
    private static final String R_IMAGE= "image";
    private static final  String R_STATE= "state";
    private static final String R_DESCRIPTION ="description";
    private static final String R_HIGHLIGHT="highlight";
    private static final String R_ACCOMMODATION ="accommodation";
    private static final String R_TRANSPORTAION = "transportation";
    private static final String R_BEST_TIME="besttime";
    private static final String R_INSTRUCTION ="instruction";
    private static final String R_NAME = "name";
    public static final String TABLE_CATEGORY = "table_category";
    public static final String TABLE_FAVORITES = "table_favorites";
    public static final String TABLE_RECIPE ="table_recipe";
    private SQLiteDatabase db = getWritableDatabase();

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.d("DB", "Constructor");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "onCreate");
        createTableFavorite(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS table_favorites");
        createTableFavorite(db);
    }


    private void createTableFavorite(SQLiteDatabase db){
        db.execSQL("CREATE TABLE table_favorites (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "image TEXT," +
                "description TEXT," +
                "highlight TEXT, " +
                "accommodation  TEXT," +
                "transportation TEXT, " +
                "besttime TEXT, "+
                "state INTEGER )");

    }

   public Modules addOneFavorite(Modules recipe) {
        ContentValues values = new ContentValues();
        values.put(R_ID, recipe.getId());
        values.put(R_NAME, recipe.getPlaceName());
        values.put(R_IMAGE, recipe.getPlaceImage());
        values.put(R_DESCRIPTION, recipe.getDesc());
        values.put(R_ACCOMMODATION, recipe.getAccommodation());
        values.put(R_HIGHLIGHT, recipe.getHighlight());
        values.put(R_TRANSPORTAION, recipe.getTransportation());
        values.put(R_BEST_TIME, recipe.getBesttiem());
        values.put(R_STATE, recipe.getState());
        this.db.insert(TABLE_FAVORITES, null, values);
        return recipe;
    }

/*
    public Modules addOneFavorite(Modules recipe) {
        ContentValues values = new ContentValues();
        values.put(R_ID, recipe.sid);
        values.put(R_NAME, recipe.sname);
        values.put(R_IMAGE, recipe.simage);
        values.put(R_DESCRIPTION, recipe.sdesc);
        values.put(R_ACCOMMODATION, recipe.sacco);
        values.put(R_HIGHLIGHT, recipe.shign);
        values.put(R_TRANSPORTAION, recipe.strans);
        values.put(R_BEST_TIME, recipe.sbest);
        values.put(R_STATE, recipe.sState);
        this.db.insert(TABLE_FAVORITES, null, values);
        return recipe;
    }
*/

    public List<Modules> getAllFavorites() {
        List<Modules> list = new ArrayList();
        Cursor cursor = this.db.rawQuery("SELECT * FROM table_favorites", null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Modules r = new Modules();
                    r.setId(cursor.getInt(cursor.getColumnIndex(R_ID)));
                    r.setPlaceName(cursor.getString(cursor.getColumnIndex(R_NAME)));
                    r.setPlaceImage(cursor.getString(cursor.getColumnIndex(R_IMAGE)));
                    r.setAccommodation(cursor.getString(cursor.getColumnIndex(R_ACCOMMODATION)));
                    r.setBesttiem(cursor.getString(cursor.getColumnIndex(R_BEST_TIME)));
                    r.setTransportation(cursor.getString(cursor.getColumnIndex(R_TRANSPORTAION)));
                    r.setState(cursor.getInt(cursor.getColumnIndex(R_STATE)));
                    r.setDesc(cursor.getString(cursor.getColumnIndex(R_DESCRIPTION)));
                    r.setHighlight(cursor.getString(cursor.getColumnIndex(R_HIGHLIGHT)));
                    list.add(r);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public void deleteFavorites(Modules recipe) {
        this.db.delete(TABLE_FAVORITES, "id = ?", new String[]{String.valueOf(recipe.getId()+ "")});
    }

    public boolean isFavoritesExist(Integer id) {
        Cursor cursor = this.db.rawQuery("SELECT * FROM table_favorites WHERE id = ?", new String[]{id + ""});
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        }
        return false;
    }

    private List<Modules> getAll(String table) {
        List<Modules> list = new ArrayList();
        return getAllFormCursor(this.db.rawQuery("SELECT * FROM " + table, null));
    }

    private List<Modules> getAllFormCursor(Cursor cursor) {
        List<Modules> list = new ArrayList();
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Modules r = new Modules();
                    r.setId(cursor.getInt(cursor.getColumnIndex(R_ID)));
                    r.setPlaceName(cursor.getString(cursor.getColumnIndex(R_NAME)));
                    r.setPlaceImage(cursor.getString(cursor.getColumnIndex(R_IMAGE)));
                    r.setAccommodation(cursor.getString(cursor.getColumnIndex(R_ACCOMMODATION)));
                    r.setBesttiem(cursor.getString(cursor.getColumnIndex(R_BEST_TIME)));
                    r.setTransportation(cursor.getString(cursor.getColumnIndex(R_TRANSPORTAION)));
                    r.setState(cursor.getInt(cursor.getColumnIndex(R_STATE)));
                    r.setDesc(cursor.getString(cursor.getColumnIndex(R_DESCRIPTION)));
                    r.setHighlight(cursor.getString(cursor.getColumnIndex(R_HIGHLIGHT)));
                    list.add(r);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }
   /* public List<Modules> getRecipesByCategoryId(Category category) {
        List<Modules> list = new ArrayList();
        return getAllFormCursor(this.db.rawQuery("SELECT  * FROM table_recipe WHERE category = ? ORDER BY date_create DESC", new String[]{category.id + ""}));
    }*/

  /*  public List<Recipe> getRecipesByCategoryId(Category category, int limit, int offset) {
        List<Recipe> list = new ArrayList();
        String q = "SELECT  * FROM table_recipe WHERE category = ? ORDER BY date_create DESC  LIMIT " + limit + " OFFSET " + offset;
        return getAllFormCursor(this.db.rawQuery(q, new String[]{category.id + ""}));
    }*/

   /* public long getRecipesCount() {
        return DatabaseUtils.queryNumEntries(this.db, TABLE_RECIPE);
    }

    public long getCategoriesCount() {
        return DatabaseUtils.queryNumEntries(this.db, TABLE_CATEGORY);
    }

    public int getRecipesByCategoryIdCount(Category category) {
        Cursor cursor = this.db.rawQuery("SELECT id FROM table_recipe WHERE category = ? ", new String[]{category.id + ""});
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<Category> addListCategory(List<Category> categories) {
        for (Category c : categories) {
            addOneCategory(this.db, c);
        }
        return getAllCategory();
    }*/

   /* public List<Category> getAllCategory() {
        return getAllCategory(TABLE_CATEGORY);
    }*/

  /*  public List<Category> getCategoriesByPage(int limit, int offset) {
        return getCategoriesFormCursor(this.db.rawQuery("SELECT * FROM table_category ORDER BY name DESC  LIMIT " + limit + " OFFSET " + offset, null));
    }

    public List<Category> searchCategories(String keyword) {
        return getCategoriesFormCursor(this.db.rawQuery("SELECT * FROM table_category WHERE LOWER(name) LIKE ? OR LOWER(description) LIKE ?", new String[]{"%" + keyword.toLowerCase() + "%", "%" + keyword.toLowerCase() + "%"}));
    }*/

   /* private void addOneCategory(SQLiteDatabase db, Category category) {
        ContentValues values = new ContentValues();
        values.put("id", category.id);
        values.put("name", category.name);
        values.put(C_BANNER, category.banner);
        values.put(C_DESCRIPTION, category.description);
        values.put(C_RECIPES, category.recipes);
        db.insert(TABLE_CATEGORY, null, values);
    }*/



 /*   private List<Category> getAllCategory(String table) {
        return getCategoriesFormCursor(this.db.rawQuery("SELECT * FROM " + table + " ORDER BY " + "name" + " DESC", null));
    }
*/
  /*  private List<Category> getCategoriesFormCursor(Cursor cursor) {
        List<Category> list = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                Category c = new Category();
                c.id = cursor.getInt(cursor.getColumnIndex("id"));
                c.name = cursor.getString(cursor.getColumnIndex("name"));
                c.banner = cursor.getString(cursor.getColumnIndex(C_BANNER));
                c.description = cursor.getString(cursor.getColumnIndex(C_DESCRIPTION));
                c.recipes = cursor.getInt(cursor.getColumnIndex(C_RECIPES));
               c.recipe_list = getRecipesByCategoryId(c);
                list.add(c);
            } while (cursor.moveToNext());
        }
        return list;
    }*/

}
