package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class GsonEditor {

    public static GsonEditor instance = null;

    public static GsonEditor getInstance(String str) {
        if (instance == null)
            instance = new GsonEditor(str);

        return instance;
    }

    private final String filename;

    private GsonEditor(String filename) {
        gson = new Gson();
        this.filename = filename;
    }

    private final Gson gson;
    private TreeMap <Integer,Record> data = new TreeMap<>();

    public void parseGson(){
        Type type = new TypeToken<TreeMap<Integer,Record>>() {}.getType();
        Object temp = gson.fromJson(filename, type);
        if(temp != null){
            data = gson.fromJson(filename, type);
        }else {
            data = new TreeMap<>();
        }
    }

    public void addRecord(int id, Record record){
        data.put(id,record);
    }

    public String saveData(){
        return gson.toJson(data);
    }

}
