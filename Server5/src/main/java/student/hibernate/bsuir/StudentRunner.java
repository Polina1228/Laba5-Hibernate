package student.hibernate.bsuir;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class StudentRunner {

    private static SessionFactory sessionFactory;

    public static void Factory() {

        sessionFactory = new Configuration().configure().buildSessionFactory();

        StudentRunner studentRunner = new StudentRunner();
        System.out.println("List of students");
        List<Student> students = studentRunner.listStudents();



        for (Student student : students) {
            System.out.println(student);
        }
        //studentRunner.addStudent("1","1","1",2,1);
        //System.out.println("===================================");
        //System.out.println("Removing Some student and updating them");
        //studentRunner.updateStudent(18, 44);
        //studentRunner.removeStudent(18);
        System.out.println("Final list of students");
        //students = studentRunner.listStudents();
        //for (Student student : students) {
        //    System.out.println(student);
        //}
        //System.out.println("===================================");
    }

    public static void addStudent(String name, String surname, String middleName, int age, int groupNum) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Student student = new Student(name, surname, middleName, age, groupNum);
        session.save(student);
        transaction.commit();
        session.close();
    }

    public static List<Student> listStudents() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List<Student> students = session.createQuery("FROM Student").list();
        transaction.commit();
        session.close();
        return students;
    }

    public static void updateStudent(int id, String name, String surname, String middleName, int age, int groupNum) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        student.setName(name);
        student.setSurname(surname);
        student.setMiddleName(middleName);
        student.setAge(age);
        student.setGroupNum(groupNum);
        session.update(student);
        transaction.commit();
        session.close();
    }

    public static void removeStudent(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
    }
}
