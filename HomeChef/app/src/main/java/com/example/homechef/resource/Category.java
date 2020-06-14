package com.example.homechef.resource;

public class Category {

    private int idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    public Category(Category categ){
        this.idCategory=categ.getIdCategory();
        this.strCategory=categ.getStrCategory();
        this.strCategoryThumb=categ.getStrCategoryThumb();
        this.strCategoryDescription=categ.getStrCategoryDescription();
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }


}
