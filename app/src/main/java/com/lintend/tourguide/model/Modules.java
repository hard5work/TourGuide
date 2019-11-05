package com.lintend.tourguide.model;

public class Modules {
    int id;
    String fav;
    public String sid = "";
    public String sState="";
    public String sname = "";
    public String simage="";
    public String sacco = "";
    public String sbest="";
    public String strans = "";
    public String sdesc="";
    public String shign = "";

    String image, text;
    int images;
    String placeName, placeImage, desc , highlight, accommodation, transportation,besttiem;
    int state;

    public Modules(int id) {
        this.id = id;
    }

    public int getImages() {
        return images;
    }

    public Modules(){

    }
    public void setImages(int images) {
        this.images = images;
    }

    public Modules(int id, String placeName, String placeImage, String desc, String highlight, String accommodation, String transportation, String besttiem, int state) {
        this.id = id;
        this.placeName = placeName;
        this.placeImage = placeImage;
        this.desc = desc;
        this.highlight = highlight;
        this.accommodation = accommodation;
        this.transportation = transportation;
        this.besttiem = besttiem;
        this.state = state;
    }

    public Modules(String text, int images) {
        this.text = text;
        this.images = images;
    }

    public Modules(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getBesttiem() {
        return besttiem;
    }

    public void setBesttiem(String besttiem) {
        this.besttiem = besttiem;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Modules(int images, String desc) {
        this.images = images;
        this.desc = desc;
    }
}
