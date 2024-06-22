package com.codinghints.eventsmicroservice.enumeration;

import static com.codinghints.eventsmicroservice.constants.Constants.*;

public enum Authority {
    USER(USER_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES),
    MANAGER(MANAGER_AUTHORITIES);

    private String value;

    Authority(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
