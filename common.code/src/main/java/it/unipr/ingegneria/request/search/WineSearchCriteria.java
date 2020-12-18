package it.unipr.ingegneria.request.search;

import java.io.Serializable;

/**
 * The {@code WineSearchCriteria} class defines the criteria of request with params for search User .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineSearchCriteria implements Serializable {
    private boolean selectAll;
    private String name;
    private Integer year;

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

    public Integer getYear() {
        return year;
    }

    public WineSearchCriteria setYear(Integer year) {
        this.year = year;
        return this;
    }
}
