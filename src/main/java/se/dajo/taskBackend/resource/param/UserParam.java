package se.dajo.taskBackend.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class UserParam {

    @QueryParam("firstName")
    @DefaultValue("no firstName")
    private String firstName;

    @QueryParam("surName")
    @DefaultValue("no surName")
    private String surName;

    @QueryParam("userNumber")
    @DefaultValue("0")
    private Long userNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Long getUserNumber() {
        return userNumber;
    }
}
