package task3;

public final class Student {
    private final int id;
    private final String name;
    private final double grade;

    public Student(int id,String name,double grade){
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше нуля !!!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым !!!");
        }
        if (grade < 0.0 || grade > 10.0) {
            throw new IllegalArgumentException("Оценка должна быть от 0.0 до 10.0 !!!");
        }
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object toCompare){
        if (this == toCompare){
            return true;
        }
        if (toCompare == null || getClass() != toCompare.getClass()) {
           return false;
        }
        Student temporaryStudentick = (Student) toCompare;
        return (temporaryStudentick.id == id &&
                Double.compare(temporaryStudentick.grade,grade) ==0 && temporaryStudentick.name.equals(name));
    }
    @Override
    public int hashCode(){
        return java.util.Objects.hash(id,name,grade);
    }
}
