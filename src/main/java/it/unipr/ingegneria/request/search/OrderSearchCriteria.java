package it.unipr.ingegneria.request.search;

import java.io.Serializable;

public class OrderSearchCriteria implements Serializable {
    private boolean selectAll;

    public boolean isSelectAll() {
        return selectAll;
    }

    public OrderSearchCriteria setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        return this;
    }
}
