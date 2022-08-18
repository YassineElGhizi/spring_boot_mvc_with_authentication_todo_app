package com.advanced_to_list.advanced_to_list.FormValidation;

public class SearchForm {
    public String name;

    @Override
    public String toString() {
        return "SearchForm{" + "name='" + name + '\'' + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SearchForm(String name) {
        this.name = name;
    }
}
