import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BookStoreGUI extends Application
{	
	// the first stage to chose the login
	private Stage adminUserLoginStage;
	
	// the stage for the user login
	private Stage userLoginStage;
	
	// the stage for admin
	private Stage adminLoginStage; 
	
	// the book store
	private final BookStore bookStore = new BookStore();
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{	
		// create the stages
		userLoginStage = new CustomerLoginStage(bookStore);
		adminLoginStage = new AdminLoginStage(bookStore);
		adminUserLoginStage = new MainStage(userLoginStage,adminLoginStage);
		
		// set the admin page in user login
		((CustomerLoginStage)userLoginStage).setAdminUserLoginStage(adminUserLoginStage);
		((CustomerLoginStage)adminLoginStage).setAdminUserLoginStage(adminUserLoginStage);
		
		// set primary stage
		primaryStage = adminUserLoginStage;
		
		// show
		primaryStage.show();
	}
	
	// method to set the button properties
	public static void setButtonProperties(Button button)
	{
		// set properties to this button
		button.setStyle("-fx-background-color: lightblue;");
		button.setTextFill(Color.DARKBLUE);
		button.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
	}
	
	// to set radio button
	public static void setRadioButtonProperties(RadioButton button)
	{
		// set properties to this button
		button.setStyle("-fx-background-color: lightblue;");
		button.setTextFill(Color.DARKBLUE);
		button.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
	}
	
	// the main method
	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}

}
