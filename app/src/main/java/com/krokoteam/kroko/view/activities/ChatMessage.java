package com.krokoteam.kroko.view.activities;

public class ChatMessage {

    private String name;
    private String text;
    private int image;

    public ChatMessage(String name, String text, int image){
        this.name = name;
        this.text = text;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
