package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.model.Number;
import sample.model.Person;
import sample.model.PersonRepository;

import java.util.List;

import sample.model.PersonRepository;

import java.util.List;

public class Controller {
    PersonRepository personRepository = new PersonRepository(Main.db.getConnection());

    private boolean IsPersonNew = false;
    private boolean IsNumberNew = false;

    private int personId;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableView<Number> numberTable;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField numberField;

    public void PeopleList() {
        ObservableList<Person> data = personTable.getItems();

        List<Person> people = personRepository.GetPeople();
        for (Person p :
                people) {
            data.add(p);
        }

        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                if (newValue != null) {
                    Person person = personTable.getSelectionModel().getSelectedItem();
                    if (person != null) {
                        firstNameField.setText(person.getName());
                        lastNameField.setText(person.getFamily());

                        personId = person.getId();

                        NumberList(personId);
                    }
                }
            }
        });

    }

    public void NumberList(int personId) {
        ObservableList<Number> data = numberTable.getItems();

        List<Number> numbers = personRepository.GetNumbersByPersonId(personId);
        for (Number n : numbers) {
            data.add(n);
        }

        numberTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Number number = numberTable.getSelectionModel().getSelectedItem();
                if (number != null) {
                    numberField.setText(number.getNumber());
                }
            }
        });
    }

    public void SavePerson() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (!IsPersonNew && person == null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "باید یک مورد را انتخاب کنید", ButtonType.OK);
            alert.show();
            return;
        }

        if (IsPersonNew) {
            AddPerson();
        } else {
            UpdatePerson();
        }

    }

    public void SaveNumber() {
        Number number = numberTable.getSelectionModel().getSelectedItem();
        if (!IsNumberNew && number == null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "باید یک مورد را انتخاب کنید", ButtonType.OK);
            alert.show();
            return;
        }
        if (IsNumberNew) {
            AddNumber();
        } else {
            UpdateNumber();
        }
    }

    public void UpdatePerson() {
        personRepository.UpdatePerson(personId, firstNameField.getText(), lastNameField.getText());
        personTable.getSelectionModel().clearSelection();
        ResetPersonTable();
    }

    public void UpdateNumber() {
        Number number = numberTable.getSelectionModel().getSelectedItem();
        personRepository.UpdateNumber(number.getId(), personId, numberField.getText());
        numberTable.getSelectionModel().clearSelection();
        ResetNumberTable();
    }

    public void DeletePerson() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "آیا از حذف اطمینان دارید ؟", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            personRepository.DeletePerson(personId);
            personRepository.DeleteNumberByPersonId(personId);
            personTable.getSelectionModel().clearSelection();
            ResetPersonTable();
        }
    }

    public void DeleteNumber() {
        Alert alert = new Alert(Alert.AlertType.NONE, "آیا از حذف اطمینان دارید ؟", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Number number = numberTable.getSelectionModel().getSelectedItem();
            personRepository.DeleteNumber(number.getId());
            numberTable.getSelectionModel().clearSelection();
            ResetNumberTable();
        }
    }

    public void NewPerson() {
        IsPersonNew = true;
        personTable.getSelectionModel().clearSelection();

        firstNameField.setText("");
        lastNameField.setText("");
    }

    public void NewNumber() {
        IsNumberNew = true;
        numberTable.getSelectionModel().clearSelection();
        numberField.setText("");
    }

    public void AddPerson() {
        personRepository.AddPerson(firstNameField.getText(), lastNameField.getText());
        personTable.getSelectionModel().clearSelection();
        ResetPersonTable();
    }

    public void AddNumber() {
        personRepository.AddNumber(numberField.getText(), personId);
        numberTable.getSelectionModel().clearSelection();
        ResetNumberTable();
    }

    public void ResetPersonTable() {
        firstNameField.setText("");
        lastNameField.setText("");
        numberField.setText("");

        ObservableList<Person> people = personTable.getItems();
        ObservableList<Number> numbers = numberTable.getItems();
        people.clear();
        numbers.clear();
        PeopleList();
        IsPersonNew = false;
    }

    public void ResetNumberTable() {
        numberField.setText("");

        ObservableList<Number> numbers = numberTable.getItems();
        numbers.clear();
        NumberList(personId);
        IsNumberNew = false;
    }

}
