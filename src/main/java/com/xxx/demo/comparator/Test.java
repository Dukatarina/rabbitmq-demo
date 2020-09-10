package com.xxx.demo.comparator;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Student student1 = new Student(21, "ahe");
        Student student2 = new Student(10, "katarina");
        Student student3 = new Student(22, "jinx");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
//        Collections.sort(students, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return 0;
//            }
//        });

        students.sort((o1, o2) -> o1.getAge() - o2.getAge() == 0 ? o1.getName().compareTo(o2.getName()) : o1.getAge() - o2.getAge());
        students.forEach(System.out::println);

        Teacher teacher = new Teacher();
        students.sort((o1, o2) -> teacher.compare(o1, o2) == 0 ? o1.getName().compareTo(o2.getName()) : teacher.compare(o1, o2));

    }
}
