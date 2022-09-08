package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleStringProperty Family;

    public Person() {
        Id=new SimpleIntegerProperty();
        Name=new SimpleStringProperty();
        Family=new SimpleStringProperty();
    }

    public int getId() {
        return Id.get();
    }

    public void setId(int id) {
        Id.set(id);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String name) {
        Name.set(name);
    }

    public String getFamily() {
        return Family.get();
    }

    public void setFamily(String family) {
        Family.set(family);
    }
}
