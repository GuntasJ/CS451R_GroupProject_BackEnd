package com.tags.cs451r_groupproject_backend.student.model;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    private StudentSortingMethod studentSortingMethod;

    public StudentComparator(StudentSortingMethod studentSortingMethod) {
        this.studentSortingMethod = studentSortingMethod;
    }

    public StudentSortingMethod getStudentSortingMethod() {
        return studentSortingMethod;
    }

    public void setStudentSortingMethod(StudentSortingMethod studentSortingMethod) {
        this.studentSortingMethod = studentSortingMethod;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return switch (studentSortingMethod) {
            case BY_ID -> Long.compare(o1.getId(), o2.getId());
            case BY_LAST_NAME -> o1.getLastName().compareTo(o2.getLastName());
            case BY_FIRST_NAME -> o1.getFirstName().compareTo(o2.getFirstName());
        };
    }
}
