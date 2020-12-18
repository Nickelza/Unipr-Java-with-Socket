package it.unipr.ingegneria.request.search;

import it.unipr.ingegneria.utils.Type;
import java.io.Serializable;

/**
 * The {@code UserSearchCriteria} class defines the criteria of request with params for search User .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserSearchCriteria implements Serializable {
    private boolean selectAll;
    private boolean makeCount;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public boolean isMakeCountAdmin() {
        return makeCount;
    }

    public UserSearchCriteria setMakeCount(boolean makeCount) {
        this.makeCount = makeCount;
        return this;
    }

    public UserSearchCriteria setUserType(String userType) {
        this.userType = userType;
        return this;
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
