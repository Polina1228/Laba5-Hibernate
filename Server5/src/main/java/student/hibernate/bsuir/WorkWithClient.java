package student.hibernate.bsuir;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkWithClient implements Runnable{
    private final Socket clientAccepted;
    private final ObjectInputStream sois;
    private final ObjectOutputStream soos;

    public WorkWithClient(Socket clientAccepted, ObjectInputStream sois, ObjectOutputStream soos) {
        this.clientAccepted = clientAccepted;
        this.sois = sois;
        this.soos = soos;
    }

    @Override
    public void run() {

        StudentRunner.Factory();
        StudentRunner.listStudents();
        System.out.println(" " + clientAccepted.getInetAddress() + " " + clientAccepted.getLocalPort() + " " + clientAccepted.getPort() );

        try {
            String clientMessageRecieved = (String) sois.readObject();
            while (!clientMessageRecieved.equals("0")) {

                System.out.println("clients message: " + clientMessageRecieved);
                switch (clientMessageRecieved) {
                    case "students": {
                        ArrayList<Student> students = new ArrayList<>(StudentRunner.listStudents());
                        for (Student student : students) {
                            soos.writeObject(student.getId()+"&"+student.getName()+"&"+student.getSurname()+"&"+student.getMiddleName()+"&"+student.getAge()+"&"+student.getGroupNum());
                            soos.flush();
                        }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "addStudent":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        StudentRunner.addStudent(answer[0], answer[1], answer[2], Integer.parseInt(answer[3]),Integer.parseInt(answer[4]));
                        break;
                    }
                    case "deleteStudent":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        StudentRunner.removeStudent(Integer.parseInt(answer[0]));
                        break;
                    }
                    case "updateStudent":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        StudentRunner.updateStudent(Integer.parseInt(answer[0]),answer[1], answer[2], answer[3], Integer.parseInt(answer[4]),Integer.parseInt(answer[5]));
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + clientMessageRecieved);
                }
                clientMessageRecieved = (String) sois.readObject();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
