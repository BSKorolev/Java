package taskM;

import taskM.datamodel.TaskData;
import taskM.datamodel.TaskItem;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;

    public TaskItem processResults() {
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        TaskItem newItem = new TaskItem(shortDescription, details, deadlineValue);
        TaskData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
