import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuestStage extends Stage 
{
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
	
	// the button to logout
	protected Button buttonLogout;
	
	// the display area
	protected ListView<String> listViewBooks;
	
	// the book store reference
	protected BookStore bookStore;
	
	// the input box
	protected VBox inputBox;
	
	// the output box
	private VBox outputBox;
	
	// the previous stage
	protected Stage previousStage;
	
	// the constructor
	public GuestStage(BookStore bookStore,Stage previousStage)
	{
		// set the bookstore
		this.bookStore = bookStore;
		// set previous stage
		this.previousStage = previousStage;
		
		// setup this stage
		setupStage();
	}
	
	// setup the stage
	private void setupStage()
	{
		// create the radio buttons
		rbSearchAuthor = new RadioButton("Author");
		rbSearchGenre = new RadioButton("Genre");
		rbSearchTitle = new RadioButton("Title");
		BookStoreGUI.setRadioButtonProperties(rbSearchAuthor);
		BookStoreGUI.setRadioButtonProperties(rbSearchGenre);
		BookStoreGUI.setRadioButtonProperties(rbSearchTitle);
		// set up the radio buttons
		setupRadioButtons();
		
		// add the radio buttons to an HBox
		HBox hBoxRadioButtons = new HBox(5);
		hBoxRadioButtons.getChildren().add(rbSearchAuthor);
		hBoxRadioButtons.getChildren().add(rbSearchTitle);
		hBoxRadioButtons.getChildren().add(rbSearchGenre);
		// set padding for this box
		hBoxRadioButtons.setPadding(new Insets(20, 10, 20, 10));
		
		// create the search button and the text field
		buttonSearch = new Button("Search");
		// set action listener
		buttonSearch.setOnAction(this::buttonSearchOnAction);
		textFieldSearch = new TextField();
		// set properties
		BookStoreGUI.setButtonProperties(buttonSearch);
		
		// create an HBox and add these to the HBox
		HBox hBoxSearch = new HBox(3);
		hBoxSearch.getChildren().add(textFieldSearch);
		hBoxSearch.getChildren().add(buttonSearch);
		// set padding for this box
		hBoxSearch.setPadding(new Insets(5));
		
		// the button to list all
		buttonListAllBooks = new Button("List All Books");
		// set properties
		BookStoreGUI.setButtonProperties(buttonListAllBooks);
		// add handlers
		buttonListAllBooks.setOnAction(this::buttonListAllBooksOnAction);
		// the logout button
		buttonLogout = new Button("Back");
		// set properties
		BookStoreGUI.setButtonProperties(buttonLogout);
		// set handler
		buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// hide this
				hide();
				// show previous
				previousStage.show();
			}
		});
		
		// create a VBox to hold the input controls
		inputBox = new VBox(10);
		
		// add all the input controls to it
		inputBox.getChildren().add(new Label("Search By: "));
		inputBox.getChildren().add(hBoxRadioButtons);
		inputBox.getChildren().add(hBoxSearch);
		inputBox.getChildren().add(buttonListAllBooks);
		inputBox.getChildren().add(new Label(""));
		inputBox.getChildren().add(new Label(""));
		inputBox.getChildren().add(buttonLogout);
		inputBox.setPadding(new Insets(10));
		
		// the output controls
		listViewBooks = new ListView<>();
		listViewBooks.setPrefSize(200, 200);
		// add this to the vbox
		outputBox = new VBox(5);
		outputBox.getChildren().add(listViewBooks);
		outputBox.setPadding(new Insets(10));
		
		// create an HBox
		HBox mainBox = new HBox(8);
		mainBox.getChildren().add(inputBox);
		mainBox.getChildren().add(outputBox);
		mainBox.setStyle("-fx-background-color: lightgray;");
		
		// set scene
		Scene scene = new Scene(mainBox,500,400);
		setScene(scene);
		
		// set title
		setTitle("Guest Login");
		
		// set operation on close
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				hide();
				previousStage.show();
			}
		});
	}
	
	// the handler for list all books
	private void buttonListAllBooksOnAction(ActionEvent actionEvent)
	{
		// the array list of books
		ArrayList<Book> searchResults = bookStore.getBooksManagement().getBooks();
		
		// remove all items from list view
		listViewBooks.getItems().removeAll( listViewBooks.getItems() );
		
		// display the results
		for( Book book : searchResults )
			listViewBooks.getItems().add( book.toString() + "\n");
	}
	
	// handler for the search button
	private void buttonSearchOnAction(ActionEvent actionEvent)
	{
		// if the text fields is empty
		if( textFieldSearch.getText().length() == 0 )
		{
			// show alert
			
			// return
			return;
		}
		
		// display all the books in the table view
		
		// the array list of books
		ArrayList<Book> searchResults = null;
		
		// if the search by author is selected
		if( rbSearchAuthor.isSelected() )
		{
			// search by author
			searchResults = bookStore.getBooksManagement().searchByAuthor( textFieldSearch.getText() );
		}
		// else if search by title is selected
		else if( rbSearchTitle.isSelected() )
		{
			// search by title
			searchResults = bookStore.getBooksManagement().searchByTitle( textFieldSearch.getText() );
		}
		else
		{
			// search by category
			searchResults = bookStore.getBooksManagement().searchByGenre( BookGenre.getEnum(textFieldSearch.getText()) );
		}
		
		// remove all items from list view
		listViewBooks.getItems().removeAll( listViewBooks.getItems() );
		
		// display the results
		for( Book book : searchResults )
			listViewBooks.getItems().add( book.toString() + "\n");
	}
	
	// method to setup the radio buttons
	private void setupRadioButtons()
	{
		// create a button groud
		ToggleGroup group = new ToggleGroup();
		group.getToggles().add(rbSearchAuthor);
		group.getToggles().add(rbSearchTitle);
		group.getToggles().add(rbSearchGenre);
		
		// set author selected to true
		rbSearchAuthor.setSelected(true);
	}
}
