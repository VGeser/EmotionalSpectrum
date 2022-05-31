package group_project.main.emotionalspectrum;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    private String filename;

    private GsonEditor(String filename) {
        gson = new Gson();
        this.filename = filename;
    }

    private final Gson gson;
    private static TreeMap<Integer,Record> data = new TreeMap<>();

    public void parseGson(String filename){
        if(!filename.equals("")) {
            this.filename = filename;
        }
        Type type = new TypeToken<TreeMap<Integer,Record>>() {}.getType();
        Object temp = gson.fromJson(filename, type);
        if(temp != null){
            data = gson.fromJson(filename, type);
        }else {
            data = new TreeMap<>();
            Log.println(Log.ERROR,"PROBLEM!!!!","I can't read data");
        }
        Log.println(Log.ERROR,"Editors' data",data.toString());
    }

    public Map<Integer,Record> flatten(){
        return data;
    }

    public Record getRecord (int id){return data.get(id);}
//to do
    public ArrayList<Record> perMonth (String current_date) {
         ArrayList<Record> res = new ArrayList<>();
         return res;
    }

    public void clearCache (){
        data.clear();
        instance=null;
    }

    public void addRecord(int id, Record record){
        data.put(id,record);
        Log.println(Log.ERROR,"Last Record:",record.getEmotionName()+ "  "+id);
    }

    public String saveData(){
        return gson.toJson(data);
    }

}
