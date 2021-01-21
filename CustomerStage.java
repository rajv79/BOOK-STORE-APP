import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerStage extends Stage 
{
	// the current user
	private User user;
	
	// the radio buttons to choose the category
	private RadioButton rbSearchTitle;
	private RadioButton rbSearchAuthor;
	private RadioButton rbSearchGenre;
	
	// the text field to enter search
	private TextField textFieldSearch;
	
	// the button to search
	private Button buttonSearch;
	
	// the button to list all books
	private Button buttonListAllBooks;
	
	// the display area
	private TableView<String> tableViewBooks;
	
	// the constructor
	public CustomerStage(User user)
	{
		// set the user
		this.user = user;
		
		// setup the stage
		setupStage();
	}
	
	// the method to setup the stage
	private void setupStage() 
	{
		// 
	}
}
