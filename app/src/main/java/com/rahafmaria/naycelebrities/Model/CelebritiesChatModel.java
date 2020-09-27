package com.rahafmaria.naycelebrities.Model;

public class CelebritiesChatModel {
    public int user_id;
    public String message;
    public int type ;

    public CelebritiesChatModel(int user_id, String message, int type) {
        this.user_id = user_id;
        this.message = message;
        this.type = type;
    }
}
