package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.otus.model.Measurement;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null)
            return Collections.emptyList();

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            URL url = getClass().getClassLoader().getResource(fileName);

            if (url == null)
                throw new IOException();

            return gson.fromJson(fileReader,
                    new TypeToken<List<Measurement>>() {}.getType());

        } catch (IOException e) {
            System.out.println("Read error");
            throw new FileProcessException(e);
        }
    }
}
