package lag311.pa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**This class starts the Inventory System application.
 * Javadocs folder is located
 * FUTURE ENHANCEMENT: The error checking process on the input data could be improved upon.  Firstly, there is no way to error check data that is uploaded within the application
 * and not entered through a GUI. This leaves possibility for data errors of Parts and Products that are loaded within the application. An enhancement would be adding error checking
 * within the Part or Product classes. Secondly, the error checking is done with try/catch blocks on each editable text field in the GUI when adding or modifying Parts or Products.
 * Instead of this, a better enhancement would be if there could a scanner or listener on each text field that identifies a data exception when it is being entered and gives the user an error, as opposed to waiting for the user to click save and running error checks on
 * all the inputted data. This could result in a quicker loading time.
 * FUTURE ENHANCEMENT: For a business rule, it could be a good idea to not make the price of a Product lower than the cost of the Products.
 * @author Leigh Grover */
public class MainApplication extends Application {
    /**This method initializes the application.*/
    @Override
    public void init() {
        System.out.println("starting");
    }
/** This method starts the JavaFX lifecycle, creates a stage object and sets the scene.
 * @param stage An instance of the Stage class.*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("C482 PA");
        stage.setScene(scene);
        stage.show();
    }
/** This is the main method. It is the first entry point to the application, runs the default init method and calls the stop method when the app is closed.
 * @param args Parameters of type String.*/
    public static void main(String[] args)
    {
        launch(args);
    }
/** This is the stop method. It closes the application when the user exits.*/
    @Override
    public void stop() {
        System.out.println("stopping");
    }
}