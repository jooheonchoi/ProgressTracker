package persistence;

import com.google.gson.Gson;
import model.ListOfSubjects;

import java.io.FileWriter;
import java.io.IOException;

// Represents tools that save and write onto a json file.
public class Saver {

    public static void saveListOfSubject(ListOfSubjects listOfSubjects, String fileName) throws IOException {
        Gson gson = new Gson();
        gson.toJson(listOfSubjects, new FileWriter(fileName)); // Inspired by gson tutorial by mkyoung
    }
}

//        String serialized = gson.toJson(listOfSubjects);
//        try {
//            FileWriter fileWriter = new FileWriter(fileName);
//            fileWriter.write(serialized);
//            fileWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

