package com.example.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

public class GsonEditor {

    public static GsonEditor instance = null;

    public static GsonEditor getInstance(String str) {
        if (instance == null)
            instance = new GsonEditor(str);

        return instance;
    }

    public static GsonEditor getInstance() {
        return instance;
    }

    private final String filename;

    private GsonEditor(String filename) {
        gson = new Gson();
        this.filename = filename;
    }

    private final Gson gson;
    private static TreeMap <Integer,Record> data = new TreeMap<>();

    public void parseGson(){
        Type type = new TypeToken<TreeMap<Integer,Record>>() {}.getType();
        Object temp = gson.fromJson(filename, type);
        if(temp != null){
            data = gson.fromJson(filename, type);
        }else {
            data = new TreeMap<>();
        }
    }

    public Map<Integer,Record> flatten(){
        return data;
    }

    public void addRecord(int id, Record record){
        if(id<=5){
            data.put(id,record);
        }else {
            String d1 = data.get(id - 6).getDate();
            String d2 = data.get(id - 1).getDate();
            if(d1.equals(d2))
                data.put(id,record);
        }
        Log.println(Log.ERROR,"Last Record:",record.getEmotionName()+ "  "+id);
    }

    public String saveData(){
        return gson.toJson(data);
    }

}
