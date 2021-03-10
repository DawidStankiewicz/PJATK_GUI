package gui_01;

public class Main {
    public static void main(String args[]) {
        Person p1 = new Person("Jan", 50);
        Student s1 = new Student("Jasiek", 20);
        Person p3 = (Person) s1;
        p1.sayHelloTo(p3); //Hi  Jasiek!
        p3.sayHelloTo(p1); //Hi Jan!
        Subject s = new Subject("GUI");
        s.setTeacher(p1);
        try {
            s.addStudent(s1);
        } catch (TooManyStudentsException e) {
            e.printStackTrace();
        }
        System.out.println(s); //GUI , teacher: Jan , students: Jasiek
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHelloTo(Person person) {
        System.out.println("Hi\t" + person.name + "!");
    }

    public String getName() {
        return name;
    }
}

class Student extends Person {

    public Student(String name, int age) {
        super(name, age);
    }

    public void sayHelloTo(Person person) {
        System.out.println("Hi " + person.getName() + "!");
    }
}

class Subject {
    private String name;
    private Person teacher;
    private Person[] students;
    private int studentsLeft = 10;

    public Subject(String name) {
        this.name = name;
        students = new Student[studentsLeft];
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Person student) {
        if (studentsLeft == 0) {
            throw new TooManyStudentsException();
        }
        int i = this.students.length - studentsLeft;
        studentsLeft--;
        this.students[i] = student;
    }

    public String toString() {
        return this.name + " , teacher: " + this.teacher.getName() + " , students: " + getStudentsString();
    }

    private String getStudentsString() {
        String result = "";
        for (int i = 0; i < students.length - studentsLeft; i++) {
            result += students[i].getName();
        }
        return result;
    }
}

class TooManyStudentsException extends RuntimeException {

}