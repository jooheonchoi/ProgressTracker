package persistence;

import com.google.gson.Gson;
import model.ListOfSubjects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Represents tools that save and write onto a json file.
public class Writer {

    // MODIFIES: file
    // EFFECTS: saves a given list of subjects to a given json file
    // Citation: Inspired by tutorial by mkyoung
    public static void saveListOfSubject(ListOfSubjects listOfSubjects, String fileName) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(fileName);
        gson.toJson(listOfSubjects, fileWriter);
        fileWriter.flush(); // Inspired by tutorial by mkyoung
    }
}



