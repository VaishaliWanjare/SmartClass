package com.example.smartclassapp.PaperView;

public class item {

    String name;
    String file;


    @Override
    public String toString() {
        return name;
    }

    public item(){}

    public item(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
