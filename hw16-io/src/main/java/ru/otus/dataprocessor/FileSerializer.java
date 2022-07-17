package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(fileName)){
            System.out.println(gson.toJson(data));
            gson.toJson(data, fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Write error");
            throw new FileProcessException(e);
        }
    }
}
