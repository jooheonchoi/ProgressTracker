package model;

import java.util.LinkedList;

// A list of subjects of a given user.
public class ListOfSubjects {
    private LinkedList<Subject> listOfSubjects;

    // EFFECTS: constructs a new list of subjects
    public ListOfSubjects() {
        listOfSubjects = new LinkedList<Subject>();
    }

//    // EFFECTS: returns username
//    public String getUserName() {
//        return userName;
//    }

    // EFFECTS: returns list of subjects
    public LinkedList<Subject> getListOfSubjects() {
        return listOfSubjects;
    }

    // MODIFIES: this
    // EFFECTS: add a subject to the beginning of the list
    public void addSubject(Subject subject) {
        listOfSubjects.addFirst(subject);
    }

    // EFFECTS: return true if the list of subjects is empty
    public boolean isEmpty() {
        return (listOfSubjects.isEmpty());
    }

    // MODIFIES: this
    // EFFECTS: remove a subject with given name; if no such subject exists, don't do anything
    public int removeSubject(String name) {
        for (Subject next : listOfSubjects) {
            if (name.equals(next.getName())) {
                listOfSubjects.remove(next);
                return 1;
            }
        }
        return -1;
    }
}

//    public void save() {
//        JSONObject obj = new JSONObject();
//        JSONArray list = new JSONArray();
//
//        obj.put("name", userName);
//        obj.put("list", list);
//
//        for (Subject next : getListOfSubjects()) {
//             JSONObject subjectObject = new JSONObject();
//             subjectObject.put("subject", next.getName());
//             subjectObject.put("bigGoal", next.getBigGoal());
//             JSONArray updateList = new JSONArray();
//             for (Update update : next.getUpdateLog()) {
//                 updateList.add(update);
//             }
//             subjectObject.put("updateLog", updateList);
//        }
//        try (FileWriter file = new FileWriter("test1.json")) {
//            file.write(obj.toString());
//            file.flush();
//        } catch (IOException e) {
//            System.out.println("didn't work");
//        }
//    }




