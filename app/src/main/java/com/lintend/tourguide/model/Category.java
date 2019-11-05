package com.lintend.tourguide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    public String banner;
    public String description;
    public Integer id;
    public String name;
    public List<Recipe> recipe_list = new ArrayList();
    public Integer recipes;
}