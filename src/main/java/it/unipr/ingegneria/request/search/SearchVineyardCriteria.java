package it.unipr.ingegneria.request.search;

import java.io.Serializable;

public class SearchVineyardCriteria implements Serializable {
    public boolean isSelectAll() {
        return selectAll;
    }

    public SearchVineyardCriteria setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        return this;
    }

    public String getName() {
        return name;
    }

    public SearchVineyardCriteria setName(String name) {
        this.name = name;
        return this;
    }

    private boolean selectAll;
    private String name;

}
