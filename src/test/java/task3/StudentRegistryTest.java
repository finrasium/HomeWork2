package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class StudentRegistryTest {

    private Student s1, s2, s3, s4;
    private Map<Integer, Student> hashMap;
    private TreeMap<Integer, Student> treeMap;

    @BeforeEach
    void setUp() {
        s1 = new Student(1, "Саша", 9.5);
        s2 = new Student(3, "Майкл", 7.0);
        s3 = new Student(5, "Кузя", 5.5);
        s4 = new Student(10, "Гришко", 10.0);

        hashMap = new HashMap<>();
        hashMap.put(s1.getId(), s1);
        hashMap.put(s2.getId(), s2);
        hashMap.put(s3.getId(), s3);
        hashMap.put(s4.getId(), s4);

        treeMap = new TreeMap<>((a, b) -> b - a);
        treeMap.putAll(hashMap);
    }
    @Test
    void findStudentsByGradeRangeNormal() {
        List<Student> result = StudentRegistry.findStudentsByGradeRange(hashMap, 7.0, 9.5);
        assertEquals(2, result.size(), "2 студента");
        assertTrue(result.contains(s1), "Саша 9.5 в списке");
        assertTrue(result.contains(s2), "Майкл 7.0 в списке");
    }
    @Test
    void findStudentsByGradeRangeEmpty() {
        Map<Integer, Student> emptyMap = new HashMap<>();
        List<Student> result = StudentRegistry.findStudentsByGradeRange(emptyMap, 0.0, 10.0);

        assertTrue(result.isEmpty(), "Студентов вообще нет. Свиток воина дракона оказался пуст!");
    }
    @Test
    void findStudentsByGradeRangeNoMatches() {
        List<Student> result = StudentRegistry.findStudentsByGradeRange(hashMap, 0.0, 4.0);

        assertTrue(result.isEmpty(), "Студентов, удовлетворяющих условию, нет! Свиток воина дракона оказался пуст!");
    }
    @Test
    void findStudentsByGradeRangeInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () ->
                        StudentRegistry.findStudentsByGradeRange(hashMap, 8.0, 5.0),
                "исключение, если min>max");
        assertThrows(IllegalArgumentException.class, () ->
                        StudentRegistry.findStudentsByGradeRange(hashMap, -1.0, 5.0),
                "исключение, если min<0");
        assertThrows(IllegalArgumentException.class, () ->
                        StudentRegistry.findStudentsByGradeRange(hashMap, 5.0, 11.0),
                "исключение, если max>10");
    }

    @Test
    void getTopNStudentsByIdNormal() {
        List<Student> result = StudentRegistry.getTopNStudentsById(treeMap, 2);
        assertEquals(2, result.size(), "Должно вернуться ровно 2 студента");
        assertEquals(s4, result.get(0), "Первым должен идти студент с id=10");
        assertEquals(s3, result.get(1), "Вторым должен идти студент с id=5");
    }
    @Test
    void getTopNStudentsByIdInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () ->
                        StudentRegistry.getTopNStudentsById(treeMap, -1),
                "Должно выброситься исключение при отрицательном n");
    }
    @Test
    void sourceMapsAreNotModified() {
        int originalHashMapSize = hashMap.size();
        StudentRegistry.findStudentsByGradeRange(hashMap, 0.0, 10.0);
        assertEquals(originalHashMapSize, hashMap.size(), "HashMap не должен была измениться");

        int originalTreeMapSize = treeMap.size();
        StudentRegistry.getTopNStudentsById(treeMap, 2);
        assertEquals(originalTreeMapSize, treeMap.size(), "TreeMap не должен был измениться");
    }
}