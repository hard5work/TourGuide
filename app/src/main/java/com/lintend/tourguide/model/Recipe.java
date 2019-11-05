package com.lintend.tourguide.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    public Integer category;
    public String category_name;
    public Long date_create;
    public Integer duration;
    public Integer id;
    public String image;
    public String instruction;
    public String name;
}