package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithServer {

    public static ObservableList<Student> DBTableStudent(){
        List<Student> students = new ArrayList<>();
        String answer;
        try{
            Main.coos.writeObject("students");
            while (!(answer = (String)Main.cois.readObject()).equals("end")){
                String [] answerArr = answer.split("&");
                students.add(new Student(Integer.parseInt(answerArr[0]) , answerArr[1], answerArr[2] , answerArr[3] , Integer.parseInt(answerArr[4]), Integer.parseInt(answerArr[5]) ));
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Student> students1 = FXCollections.observableArrayList(students);
        return students1;
    }

    public static void AddStudent(String name,String surname,String middleName,int age,int groupNum){
        try {
            Main.coos.writeObject("addStudent");
            Main.coos.writeObject(name+"&"+surname+"&"+middleName+"&"+age+"&"+groupNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateStudent(int id,String name,String surname,String middleName,int age,int groupNum){
        try {
            Main.coos.writeObject("updateStudent");
            Main.coos.writeObject(id+"&"+name+"&"+surname+"&"+middleName+"&"+age+"&"+groupNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteStudent(int id){
        try {
            Main.coos.writeObject("deleteStudent");
            Main.coos.writeObject(id+"&");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
