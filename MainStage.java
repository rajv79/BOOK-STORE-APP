import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage extends Stage
{
	// the page for the user login
	private Stage userLoginStage;
	private Stage adminLoginStage;
	
	// the constructor
	public MainStage(Stage userLoginStage,Stage adminLoginStage)
	{
		// set the user login stage
		this.userLoginStage = userLoginStage;
		this.adminLoginStage = adminLoginStage;
		
		// set the title
		setTitle("Welcome to The Bookstore");
		
		// setup the stage
		setupStage();
	}
	
	// method to setup the stage
	private void setupStage()
	{
		// // the scene in this stage is of two buttons
		
		// the first button is for admin login
		Button buttonAdminLogin = new Button("Admin Login");
		// set properties to this button
		BookStoreGUI.setButtonProperties(buttonAdminLogin);
		// set x and y translation
		buttonAdminLogin.setTranslateX(140);
		buttonAdminLogin.setTranslateY(140);
		
		// the other button for customer login
		Button buttonCustomerLogin = new Button("Customer Login");
		// set properties to this button
		BookStoreGUI.setButtonProperties(buttonCustomerLogin);
		// set x and y translation
		buttonCustomerLogin.setTranslateX(160);
		buttonCustomerLogin.setTranslateY(140);
		
		// handlers to the buttons
		buttonCustomerLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hide();
				userLoginStage.show();
			}
		});
		
		buttonAdminLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hide();
				adminLoginStage.show();
			}
		});
		
		// create a flow pane
		FlowPane pane = new FlowPane(buttonAdminLogin,buttonCustomerLogin );
		// set the background
		pane.setStyle("-fx-background-color: lightgray;");
		// set border
		pane.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		// create the scene
		setScene(new Scene(pane, 500,300));
		// set resizeable to false
		setResizable(false);
	}
}
