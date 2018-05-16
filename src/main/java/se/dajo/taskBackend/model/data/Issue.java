package se.dajo.taskBackend.model.data;

public class Issue {

    protected Issue (){
    }

    private String description;

    public Issue(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
