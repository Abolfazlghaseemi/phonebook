package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Number {
    private SimpleIntegerProperty Id;
    private SimpleIntegerProperty personId;
    private SimpleStringProperty Number;

    public Number() {
        Id=new SimpleIntegerProperty();
        personId=new SimpleIntegerProperty();
        Number=new SimpleStringProperty();
    }

    public int getId() {
        return Id.get();
    }

    public void setId(int id) {
        Id.set(id);
    }

    public int getPersonId() {
        return personId.get();
    }

    public void setPersonId(int personId) {
        this.personId.set(personId);
    }

    public String getNumber() {
        return Number.get();
    }

    public void setNumber(String number) {
        Number.set(number);
    }
}
