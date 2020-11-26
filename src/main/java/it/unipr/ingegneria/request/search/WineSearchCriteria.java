package it.unipr.ingegneria.request.search;

import java.io.Serializable;

public class WineSearchCriteria implements Serializable {
    private boolean selectAll;
    private String name;
    private int year;

    public boolean isSelectAll() {
        return selectAll;
    }

    public WineSearchCriteria setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        return this;
    }

    public String getName() {
        return name;
    }

    public WineSearchCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public WineSearchCriteria setYear(int year) {
        this.year = year;
        return this;
    }
}
