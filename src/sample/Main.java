package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Database;

public class Main extends Application {
    public static Database db;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent root = loader.load();

        Controller controller=loader.getController();
        controller.PeopleList();

        primaryStage.setTitle("دفترچه تلفن");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        db=new Database();
        db.Open();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        db.Close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
