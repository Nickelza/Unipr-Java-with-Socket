package it.unipr.ingegneria.request.search;

import it.unipr.ingegneria.util.Type;

import java.io.Serializable;

public class UserSearchCriteria implements Serializable {
    private boolean selectAll;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public UserSearchCriteria setUserType(Type userType) {
        this.userType = userType.toString();
        return this;
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    public UserSearchCriteria setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        return this;
    }
}
