import dao.TeacherDAO;
import model.Teacher;

public class TestTeacher {

    public static void main(String[] args) {

        Teacher teacher = new Teacher(
                1,
                "Per Hansen",
                "per.hansen@hk.no",
                "EMP001"
        );

        TeacherDAO dao = new TeacherDAO();

        dao.addTeacher(teacher);
    }
}
