package persistence;

import com.google.gson.Gson;
import model.ListOfSubjects;
import model.Subject;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

// Represents a reader that reads a list of subjects.
public class Reader {
//    private ListOfSubjects listOfSubjects;
//
//    public Reader() {
//        listOfSubjects = new ListOfSubjects();
//    }


    // EFFECTS: returns listofsubjects by reading json file
    public static ListOfSubjects reader(String file) throws IOException {
        Gson gson = new Gson();
        ListOfSubjects listOfSubjects = gson.fromJson(new FileReader(file), ListOfSubjects.class);
        return listOfSubjects;
    }
}

