package com.example.edgarpetrosian.getcontact.Engine;

public class ModelGetContacts {
    private long id;
    private int imagePath;
    private String name;
    private String number;
    private String uri;

    public ModelGetContacts() {
    }

    public ModelGetContacts(int imagePath, String name, String number) {
        this.imagePath = imagePath;
        this.name = name;
        this.number = number;
    }

    public ModelGetContacts(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
