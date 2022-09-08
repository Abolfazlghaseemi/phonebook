package sample.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static sample.model.TableNames.*;

public class PersonRepository {
    private Connection connection;

    public PersonRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Person> GetPeople() {

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * From " + TableNames.TABLE_PEOPLE)) {

            List<Person> People = new ArrayList<>();

            while (results.next()) {
                Person person = new Person();
                person.setId(results.getInt(TableNames.COLUMN_PEOPLE_ID));
                person.setName(results.getString(TableNames.COLUMN_PEOPLE_NAME));
                person.setFamily(results.getString(TableNames.COLUMN_PEOPLE_FAMILY));
                People.add(person);
            }
            return People;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return null;
        }
    }

    public Person GetPersonById(int personId) {
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * From " + TableNames.TABLE_PEOPLE + " WHERE "
                     + TableNames.TABLE_PEOPLE +"."+ TableNames.COLUMN_PEOPLE_ID + " = " + personId)) {

            Person person = new Person();
            person.setId(results.getInt(TableNames.COLUMN_PEOPLE_ID));
            person.setName(results.getString(TableNames.COLUMN_PEOPLE_NAME));
            person.setFamily(results.getString(TableNames.COLUMN_PEOPLE_FAMILY));

            return person;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return null;
        }
    }

    public int GetPeopleCount() {
        String query="SELECT count(*) From " + TableNames.TABLE_PEOPLE;
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {
            System.out.println(query);
            return results.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return 0;
        }
    }

    public List<Number> GetNumbers() {

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * From " + TableNames.TABLE_NUMBERS)) {

            List<Number> numbers = new ArrayList<>();

            while (results.next()) {
                Number number = new Number();
                number.setId(results.getInt(TableNames.COLUMN_NUMBERS_ID));
                number.setNumber(results.getString(TableNames.COLUMN_NUMBERS_NUMBER));
                number.setPersonId(results.getInt(TableNames.COLUMN_NUMBERS_PERSONID));
                numbers.add(number);
            }
            return numbers;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return null;
        }
    }
    public List<Number> GetNumbersByPersonId(int personId) {

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * From " + TableNames.TABLE_NUMBERS+ " WHERE "
                     + TableNames.TABLE_NUMBERS +"."+ TableNames.COLUMN_NUMBERS_PERSONID + " = " + personId)) {

            List<Number> numbers = new ArrayList<>();

            while (results.next()) {
                Number number = new Number();
                number.setId(results.getInt(TableNames.COLUMN_NUMBERS_ID));
                number.setNumber(results.getString(TableNames.COLUMN_NUMBERS_NUMBER));
                number.setPersonId(results.getInt(TableNames.COLUMN_NUMBERS_PERSONID));
                numbers.add(number);
            }
            return numbers;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return null;
        }
    }

    public Number GetNumberByPersonId(int numberId) {
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * From " + TableNames.TABLE_NUMBERS + " WHERE "
                     + TableNames.TABLE_NUMBERS +"."+ TableNames.COLUMN_NUMBERS_ID + " = " + numberId)) {

            Number number = new Number();
            number.setId(results.getInt(TableNames.COLUMN_NUMBERS_ID));
            number.setNumber(results.getString(TableNames.COLUMN_NUMBERS_NUMBER));
            number.setPersonId(results.getInt(TableNames.COLUMN_NUMBERS_PERSONID));

            return number;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return null;
        }
    }


    public boolean AddPerson(String name, String family) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + TABLE_PEOPLE + " (" + COLUMN_PEOPLE_NAME + "," + COLUMN_PEOPLE_FAMILY + ") " +
                    "values ('" + name + "','" + family + "')");
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }
    }
    public boolean UpdatePerson (int personId,String name, String family){
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE "+TABLE_PEOPLE+" SET "+COLUMN_PEOPLE_NAME+"='"+name
                    +"' , "+COLUMN_PEOPLE_FAMILY+"='"+family+"' WHERE " + COLUMN_PEOPLE_ID + "=" + personId);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }

    }
    public boolean DeletePerson (int personId){
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_PEOPLE + " WHERE " + COLUMN_PEOPLE_ID + "=" + personId);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }

    }


    public boolean AddNumber(String number, int personId) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + TABLE_NUMBERS + " (" + COLUMN_NUMBERS_NUMBER + "," + COLUMN_NUMBERS_PERSONID + ") " +
                    "values ('" + number + "','" + personId + "')");
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }
    }
    public boolean UpdateNumber (int numberId, int personId,String number){
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE "+TABLE_NUMBERS+" SET "+COLUMN_NUMBERS_NUMBER+"='"+number
                    +"' , "+COLUMN_NUMBERS_PERSONID+"="+personId+" WHERE " + COLUMN_NUMBERS_ID + "=" + numberId);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }

    }
    public boolean DeleteNumberByPersonId (int personId){
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_NUMBERS + " WHERE " + COLUMN_NUMBERS_PERSONID + "=" + personId);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }

    }
    public boolean DeleteNumber (int numberId){
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_NUMBERS + " WHERE " + COLUMN_NUMBERS_ID+ "=" + numberId);
            return true;

        } catch (SQLException e) {
            System.out.println("Error in Execute Query " + e.getMessage());
            return false;
        }

    }
}
