package com.tags.cs451r_groupproject_backend.position.model;

import java.util.Comparator;

public class PositionComparator implements Comparator<Position> {
    private PositionSortingMethod positionSortingMethod;

    public PositionComparator(PositionSortingMethod positionSortingMethod) {
        this.positionSortingMethod = positionSortingMethod;
    }

    public PositionSortingMethod getPositionSortingMethod() {
        return positionSortingMethod;
    }

    public void setPositionSortingMethod(PositionSortingMethod positionSortingMethod) {
        this.positionSortingMethod = positionSortingMethod;
    }

    @Override
    public int compare(Position o1, Position o2) {
        return switch (positionSortingMethod) {
            case BY_ID -> Long.compare(o1.getId(), o2.getId());
            case BY_DEGREE -> o1.getDegree().compareTo(o2.getDegree());
            case BY_CLASS_TYPE -> o1.getClassType().compareTo(o2.getClassType());
            case BY_NOTES -> o1.getNotes().compareTo(o2.getNotes());
            case BY_POSITION_TYPE -> o1.getPositionType().compareTo(o2.getPositionType());
            case BY_SEMESTER -> o1.getSemester().compareTo(o2.getSemester());
            case BY_COURSE_NUMBER -> o1.getCourseNumber().compareTo(o2.getCourseNumber());
        };
    }
}
