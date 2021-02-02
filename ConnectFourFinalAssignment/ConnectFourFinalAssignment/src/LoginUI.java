



import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The LoginUI object starts the login user interface.
 *         </p>
 */
public class LoginUI extends Application {

	/**
	 * load the fxml file and set it as the current scene.
	 */
	@Override
	public void start(Stage primaryStage) {

		try {
			URL u = getClass().getResource("/fxml/login.fxml");

			FXMLLoader loader = new FXMLLoader(u);
			Pane root = loader.load();
			primaryStage.setTitle("Login In");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
