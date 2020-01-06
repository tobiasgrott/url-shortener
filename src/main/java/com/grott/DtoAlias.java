package com.grott;

public class DtoAlias {
    private String key;
    private String url;

    public DtoAlias() {
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return "[key=" + this.key + ",url=" + this.url + "]";
    }
}