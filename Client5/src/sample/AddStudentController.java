package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField middleNameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField groupNumField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void okClick(){
        String name = nameField.getText();
        String surname = surnameField.getText();
        String middleName = middleNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        int groupNum = Integer.parseInt(groupNumField.getText());
        WorkWithServer.AddStudent(name, surname, middleName, age, groupNum);

        cancClick();
    }

    @FXML
    public void cancClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
