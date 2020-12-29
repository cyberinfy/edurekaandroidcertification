package com.tcs.assignmentfour.datamodel;


public class PictureDataModel {
    private String name, path, type;

    @Override
    public String toString() {
        return "PictureDataModel{" +
                "name='" + name + "\'\n" +
                "|_path='" + path + "\'\n" +
                "|_type='" + type + "\'\n" +
                '}';
    }

    public PictureDataModel(String name, String path, String type, String size) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}