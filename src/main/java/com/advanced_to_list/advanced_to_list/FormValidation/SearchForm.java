package com.advanced_to_list.advanced_to_list.FormValidation;

public class SearchForm {
    public String name;
    public String before;

    public SearchForm(String name, String before) {
        this.name = name;
        this.before = before;
    }

    @Override
    public String toString() {
        return "SearchForm{" + "name='" + name + '\'' + ", before=" + before + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getBefore() {
        return before;
    }
}
