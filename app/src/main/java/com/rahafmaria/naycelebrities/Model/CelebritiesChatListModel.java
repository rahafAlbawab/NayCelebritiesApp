package com.rahafmaria.naycelebrities.Model;

public class CelebritiesChatListModel {
    public String user_name;
    public String user_image;
    public String latest_message;
    public int user_id;

    public CelebritiesChatListModel(String user_name, String user_image, String latest_message, int user_id) {
        this.user_name = user_name;
        this.user_image = user_image;
        this.latest_message = latest_message;
        this.user_id = user_id;
    }
}
