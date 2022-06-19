package ru.otus.dataprocessor;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.otus.model.Measurement;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try (FileReader fileReader = new FileReader(Resources.getResource(fileName).getPath())) {
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
