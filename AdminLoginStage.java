import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminLoginStage extends CustomerLoginStage
{
	// the hard-coded username and password
	private static final String ADMIN_USER_NAME = "admin";
	private static final String ADMIN_PASSWORD = "cse148";
	
	// the next stage
	private Stage adminStage;
	
	public AdminLoginStage(BookStore bookStore) 
	{
		super(bookStore);
		
		// create the admin stage
		adminStage = new AdminStage(bookStore, this);
		
		// set the new user and guest login invisible
		buttonGuestLogin.setVisible(false);
		buttonNewUser.setVisible(false);
	}
	
	// the handler for the login button
	protected void loginButtonSetOnAction(ActionEvent actionEvent)
	{
		//  if the user name empty
		if( isEmpty(textFieldUsername))
		{
			// show message
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Username cannot be empty!");
			// show 
			alert.showAndWait();
			// return 
			return;
		}
		
		// if the user name empty
		if( isEmpty(textFieldPassword))
		{
			// show message
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Password cannot be empty!");
			// show 
			alert.showAndWait();
			// return 
			return;
		}
		
		// if login successful
		if( textFieldUsername.getText().equals(ADMIN_USER_NAME) && textFieldPassword.getText().equals(ADMIN_PASSWORD))
		{
			// clear the text fields
			textFieldUsername.setText("");
			textFieldPassword.setText("");
			
			// hide this
			hide();
			// show next stage
			adminStage.show();
		}
		// else
		else
		{
			// create alert and show
			//  show message
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Incorrect admin username and password!");
			// show 
			alert.showAndWait();
		}
	}
}
