package se.dajo.taskBackend.model.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class Issue {

    private String description;

    @JsonCreator
    public Issue(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
