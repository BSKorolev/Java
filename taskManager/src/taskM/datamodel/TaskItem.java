package taskM.datamodel;

import java.time.LocalDate;


public class TaskItem {

    private String shortDescription;
    private String details;
    private LocalDate deadline;

    public TaskItem(String shortDescription, String details, LocalDate deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.deadline = deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }



    public String getDetails() {
        return details;
    }



    public LocalDate getDeadline() {
        return deadline;
    }


}
