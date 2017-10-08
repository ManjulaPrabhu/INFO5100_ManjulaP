package com.neu.info5100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String studentName;
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("INFO6100"));
        courseList.add(new Course("INFO5100"));

        int studentId;
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("\n=======================Course Registration System=======================");
            System.out.println("1.Register for a Course");
            System.out.println("2.Course Status");
            System.out.println("3.Exit");
            System.out.println("Enter an option");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Available courses - Please pick your option");
                    for (int i = 0; i < courseList.size(); i++) {
                        System.out.println(i+1 + " - " + courseList.get(i).getTitle());
                    }
                    int optionSelected = new Scanner(System.in).nextInt()-1;

                    Course currentCourse = courseList.get(optionSelected);
                    System.out.println("Enter the Student name and id to register");
                    studentName = in.next();
                    studentId = in.nextInt();
                    Students studentObject = new Students(studentName, studentId);

                    currentCourse.registerStudent(studentObject);
                    break;
                case 2:
                    System.out.println("================== COURSE REGISTRATION STATUS ==============");

                    for (Course course : courseList) {
                        System.out.println("CourseID: " + course.getTitle());
                        System.out.println("STUDENT NAME & ID");
                        for(Students student : course.getStudents())
                        {
                            System.out.println(student.getName() + "    " + student.getID());
                        }
                        System.out.println("Remaining Seats: " + (10 - course.getStudents().size()));
                    }

            }
        }
    }
}

class Students {
    String name;
    int id;

    Students(String name, int id) {
        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getID() {
        return id;
    }
}

class Course {
    String courseName;

    List<Students> studentsArray = new ArrayList<Students>();

    Course(String courseName) {
        this.courseName = courseName;
    }

    List<Students> getStudents() {
        return studentsArray;
    }

    boolean isFull() {
        if (studentsArray.size() >= 10) {
            return true;
        } else {
            return false;
        }
    }

    String getTitle() {
        return courseName;
    }

    void registerStudent(Students studentObject) {
        if (!isFull()) {
            studentsArray.add(studentObject);
        } else {
            System.out.println("Cannot add the student,course is full");
        }
    }

}
