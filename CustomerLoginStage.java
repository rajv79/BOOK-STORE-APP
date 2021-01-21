import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerLoginStage extends Stage
{
	// the reference to the admin-user page
	protected Stage adminUserLoginStage;
	
	// the text fields
	protected TextField textFieldUsername;
	protected TextField textFieldPassword;
	
	// the buttons
	protected Button buttonNewUser;
	protected Button buttonGuestLogin;
	
	// the Bookstore
	private BookStore bookStore;
	
	// the guest stage
	private Stage guestStage;

	// the constructor
	public CustomerLoginStage(BookStore bookStore)
	{
		// set book store
		this.bookStore = bookStore;
		this.guestStage = new GuestStage(bookStore, this);
		
		// set title
		setTitle("Customer Login");
		
		// setup the stage
		setupStage();
	}
	
	// the method to setup this stage
	private void setupStage()
	{
		// // the three buttons
		Button buttonSignIn = new Button("Login");
		buttonNewUser = new Button("New User");
		buttonGuestLogin = new Button("Guest Login");
		Button buttonBack = new Button("Back");
		buttonBack.setAlignment(Pos.CENTER);
		buttonBack.setTranslateY(200);
		buttonBack.setTranslateX(180);
		
		// set the properties for the buttons
		BookStoreGUI.setButtonProperties(buttonSignIn);
		BookStoreGUI.setButtonProperties(buttonNewUser);
		BookStoreGUI.setButtonProperties(buttonGuestLogin);
		BookStoreGUI.setButtonProperties(buttonBack);
		
		// set size for new user and guest login buttons
		buttonNewUser.setPrefWidth(100);
		buttonGuestLogin.setPrefWidth(100);
		
		// the text fields
		textFieldUsername = new TextField();
		textFieldPassword = new TextField();
		
		// the labels
		Label username = new Label("Username");
		Label password = new Label("Password");
		
		// the grid pane
		GridPane gridPane = new GridPane();
		// set gaps
		gridPane.setVgap(3);
		gridPane.setHgap(4);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setTranslateY(100);
		
		// add controls to grid pane
		gridPane.add(username,0,0);
		gridPane.add(password,0,1);
		
		gridPane.add(textFieldUsername,1,0);
		gridPane.add(textFieldPassword,1,1);
		gridPane.add(buttonSignIn,1,2);
		
		gridPane.add(buttonNewUser,2,0);
		gridPane.add(buttonGuestLogin,2,1);
		
		// handler for the buttons
		buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// disable customer login page
				hide();
				
				// show main page
				adminUserLoginStage.show();
			}
		});
		// for the login button
		buttonSignIn.setOnAction(this::loginButtonSetOnAction);
		// set handler for guest login button
		buttonGuestLogin.setOnAction(this::guestLoginButtonSetOnAction);
		// // set handler for new user button
		buttonNewUser.setOnAction(this::newUserButtonSetOnAction);
		
		// create a VBOx
		VBox vBox = new VBox();
		// add controls to it
		vBox.getChildren().add(gridPane);
		vBox.getChildren().add(buttonBack);
		
		// set border
		vBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		// set the background
		vBox.setStyle("-fx-background-color: lightgray;");
		
		// add to the scene
		setScene(new Scene(vBox,400,400));
		
		// set resizeable to false
		setResizable(false);
	}
	
	// handler for the new user
	private void newUserButtonSetOnAction(ActionEvent actionEvent)
	{
		// if the user name empty
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
		
		// if the user-name already exists
		if( bookStore.getUsersManagement().getUser(textFieldUsername.getText()) != null )
		{
			// show message
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Username already exists!");
			// show 
			alert.showAndWait();
		}
		// else add this user to the users list
		else
		{
			bookStore.getUsersManagement().addUser(new User(textFieldUsername.getText(), textFieldPassword.getText()));
			// show message
			Alert alert = new Alert(AlertType.CONFIRMATION);
			// set context
			alert.setContentText("New user added!");
			// show 
			alert.showAndWait();
			// clear the text fields
			textFieldUsername.setText("");
			textFieldPassword.setText("");
		}
	}
	
	// the handler for guest login
	private void guestLoginButtonSetOnAction(ActionEvent actionEvent)
	{
		// hide this form
		hide();
		guestStage.show();
	}
	
	// the handler for the login button
	protected void loginButtonSetOnAction(ActionEvent actionEvent)
	{
		// if the user name empty
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
		if( bookStore.login(textFieldUsername.getText(), textFieldPassword.getText() ))
		{
			// login
			hide();
			// show guest stage
			guestStage.show();
		}
		// else show error
		else
		{
			// create alert
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Incorrect Username and Password!");
			// show 
			alert.showAndWait();
			// return 
			return;
		}
	}

	// method to check if the user name and password text fields are not empty
	protected boolean isEmpty(TextField textField)
	{
		return textField.getText().length() == 0;
	}
	
	public void setAdminUserLoginStage(Stage adminUserLoginStage) {
		this.adminUserLoginStage = adminUserLoginStage;
	}
	
	
}
