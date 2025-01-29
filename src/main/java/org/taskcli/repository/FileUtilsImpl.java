package org.taskcli.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.model.Task;
import org.taskcli.utils.LocalDateTimeTypeAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import static org.taskcli.utils.Constants.TASKS_FILE;

@Slf4j
@RequestScoped
public class FileUtilsImpl implements FileUtils {
    Gson gson =new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()) //
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY) //
            .setPrettyPrinting() //
            .create();

    public List<Task> readFile() throws IOException {
        JsonReader reader;
        reader = new JsonReader(new FileReader(TASKS_FILE));
        Type listType = new TypeToken<List<Task>>() {}.getType();
        List<Task> gsonList = gson.fromJson(reader, listType);
        if (gsonList == null) {
            gsonList = List.of();
        }
        reader.close();
        return gsonList;

    }

    public void checkIfFileExists() throws IOException {
        File file = new File(TASKS_FILE);
        if (!file.exists() || !file.isDirectory()){
            if (file.createNewFile()) {
                info("File created: " + file.getName());
            } else {
                error();
            }
        } else {
            error();
        }
    }

    public void writeListToFile(List<Task> data) throws IOException {
        Writer writer = new FileWriter(TASKS_FILE);
        gson.toJson(data, writer);
        writer.close();
    }

    private void info(String str){
        log.info(str);
        System.out.println(str);
    }

    private void error(){
        log.error("File already exists.");
        System.out.println("File already exists.");
    }
}
