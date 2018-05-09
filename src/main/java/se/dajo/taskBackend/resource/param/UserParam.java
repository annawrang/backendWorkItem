package se.dajo.taskBackend.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class UserParam {

    @QueryParam("firstName")
    @DefaultValue("0")
    private String firstName;

    @QueryParam("surName")
    @DefaultValue("0")
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
