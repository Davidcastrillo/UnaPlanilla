package cr.ac.una.unaplanilla2;

import cr.ac.una.unaplanilla2.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       FlowController.getInstance().InitializeFlow(stage,null);
        stage.getIcons().add(new Image("cr/ac/una/unaplanilla2/resources/Usuario-48.png"));
        stage.setTitle("UNA PLANILLA");
        FlowController.getInstance().goViewInWindow("LogIngView");
    }

  
    public static void main(String[] args) {
        launch();
    }

}