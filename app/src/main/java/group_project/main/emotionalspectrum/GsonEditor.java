package group_project.main.emotionalspectrum;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class GsonEditor {
    //variables
    private String filename;
    public static GsonEditor instance = null;
    private int[] freqCash;
    private int[] intensCash;
    private final Gson gson;
    private static TreeMap<Integer, Record> data = new TreeMap<>();

    //constructor
    public static GsonEditor getInstance(String str) {
        if (instance == null) {
            instance = new GsonEditor(str);
        }
        return instance;
    }

    public static GsonEditor getInstance() {
        return instance;
    }

    private GsonEditor(String filename) {
        gson = new Gson();
        this.filename = filename;
        freqCash = new int[10];
        intensCash = new int[10];
    }

    //get - set
    public int[] getFreqCash() {
        return freqCash;
    }

    public int[] getIntensCash() {
        return intensCash;
    }

    public Map<Integer, Record> flatten() {
        return data;
    }

    public Record getRecord(int id) {
        return data.get(id);
    }

    public int getDataSize() {
        return data.size();
    }

    //functional
    public void parseGson(String filename) {
        if (!filename.equals("")) {
            this.filename = filename;
        }
        Type type = new TypeToken<TreeMap<Integer, Record>>() {
        }.getType();
        Object temp = gson.fromJson(filename, type);
        if (temp != null) {
            data = gson.fromJson(filename, type);
        } else {
            data = new TreeMap<>();
        }
    }

    public ArrayList<Record> perMonth(int curPtr) {
        ArrayList<Record> res = new ArrayList<>();
        if (data.size() <= 30) {
            res.addAll(data.values());
        } else {
            for (int i = 0; i < 30; i++) {
                res.add(data.get(curPtr - i));
            }
        }
        return res;
    }

    public String saveFreq() {
        return gson.toJson(freqCash);
    }

    public String saveIntens() {
        return gson.toJson(intensCash);
    }

    public int[] loadFreq(String freqSrc) {
        Object temp = gson.fromJson(freqSrc, int[].class);
        if (temp != null) {
            freqCash = gson.fromJson(freqSrc, int[].class);
        } else {
            Arrays.fill(freqCash, 0);
        }
        return freqCash;
    }

    public int[] loadIntens(String intensSrc) {
        Object temp = gson.fromJson(intensSrc, int[].class);
        if (temp != null) {
            intensCash = gson.fromJson(intensSrc, int[].class);
        } else {
            Arrays.fill(intensCash, 0);
        }
        return intensCash;
    }

    public void clearCache() {
        data.clear();
        instance = null;
    }

    public short relateF(byte i) {
        switch (i) {
            case 13:
                return 0;
            case 3:
                return 1;
            case 7:
                return 2;
            case 23:
                return 3;
            case 19:
                return 4;
            case 5:
                return 5;
            case 2:
                return 6;
            case 11:
                return 7;
            case 29:
                return 8;
            case 17:
                return 9;
        }
        return -1;
    }

    public void addRecord(int id, Record record) {
        data.put(id, record);
        byte sector = (byte) record.getS();
        byte intensity = (byte) record.getIntensity();
        freqCash[relateF(sector)]++;
        intensCash[intensity + 1]++;
    }

    public String saveData() {
        return gson.toJson(data);
    }

}
