package com.tags.cs451r_groupproject_backend.application.model;

import java.util.Comparator;

public class ApplicationComparator implements Comparator<Application> {
    private ApplicationSortingMethod applicationSortingMethod;

    public ApplicationComparator(ApplicationSortingMethod applicationSortingMethod) {
        this.applicationSortingMethod = applicationSortingMethod;
    }

    public ApplicationSortingMethod getApplicationSortingMethod() {
        return applicationSortingMethod;
    }

    public void setApplicationSortingMethod(ApplicationSortingMethod applicationSortingMethod) {
        this.applicationSortingMethod = applicationSortingMethod;
    }

    @Override
    public int compare(Application o1, Application o2) {
        return switch (applicationSortingMethod) {
            case BY_ID -> Long.compare(o1.getId(), o2.getId());
            case BY_LAST_NAME -> o1.getLastName().compareTo(o2.getLastName());
            case BY_FIRST_NAME -> o1.getFirstName().compareTo(o2.getFirstName());
        };
    }
}
