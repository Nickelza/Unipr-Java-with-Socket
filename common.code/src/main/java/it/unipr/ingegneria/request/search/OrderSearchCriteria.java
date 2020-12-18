package it.unipr.ingegneria.request.search;

import it.unipr.ingegneria.entities.user.User;

import java.io.Serializable;

/**
 * The {@code OrderSearchCriteria} class defines the criteria of request with params for search a Order .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class OrderSearchCriteria implements Serializable {
    private boolean selectAll;
    private User searchByUser;

    public boolean isSelectAll() {
        return selectAll;
    }

    public OrderSearchCriteria setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        return this;
    }

    public User getSearchByUser() {
        return searchByUser;
    }

    public OrderSearchCriteria setSearchByUser(User searchByUser) {
        this.searchByUser = searchByUser;
        return this;
    }
}
