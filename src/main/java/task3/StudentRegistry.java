package task3;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

public class StudentRegistry {
    public static List<Student> findStudentsByGradeRange(
            Map<Integer, Student> students,
            double minGrade,
            double maxGrade) {
        if (minGrade > maxGrade || minGrade < 0.0 || maxGrade > 10.0) {
            throw new IllegalArgumentException("Некорректные границы !!!");
        }
        List<Student> validStudents = new ArrayList<>();
        for (Student studentick : students.values()) {
            double curGrade = studentick.getGrade();
            if (curGrade <= maxGrade && curGrade >= minGrade) {
                validStudents.add(studentick);
            }
        }
        return validStudents;
    }

    public static List<Student> getTopNStudentsById(
            NavigableMap<Integer, Student> students,
            int n) {
        if (n < 0){
            throw new IllegalArgumentException("Нельзя выводить отрицательное число студентов !!!");
        }
        List<Student> nStudents= new ArrayList<>();
        for (Student studentick : students.values()){
            if (nStudents.size() == n){
                break;
            }
            nStudents.add(studentick);
        }
        return nStudents;
    }
}
