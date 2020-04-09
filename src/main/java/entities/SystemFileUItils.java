package entities;

import com.google.gson.Gson;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class SystemFileUItils {

    public static Object readFile(String name, Class tClass) throws IOException {
        JsonReader jsonReader = new JsonReader(new FileReader(name));
        Object o = new Gson().fromJson(jsonReader,tClass);
        return o;
    }

    public static void writeFile(String name, Object o,Class tclass) throws IOException {
        String jsonObject = new Gson().toJson(o,tclass);
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(jsonObject);
        fileWriter.close();
    }
}
