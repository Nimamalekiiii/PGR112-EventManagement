import dao.StudentDAO;
import model.Student;

import java.util.ArrayList;

public class TestStudent {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        ArrayList<Student> students = dao.getAllStudents();

        for (Student student : students) {
            System.out.println(student);
        }
    }
}