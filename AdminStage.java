import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminStage extends GuestStage
{
	// the button to add a book
	private Button buttonAddBook;
	// the button to delete a Book
	private Button buttonDeleteBook;
	
	public AdminStage(BookStore bookStore, Stage previousStage) 
	{
		// call the super class constructor
		super(bookStore, previousStage);
		
		// setup the stage
		setupStage();	
	}

	// the method to setup stage
	private void setupStage()
	{
		// setup the buttons
		buttonAddBook = new Button("Add Book");
		// add the handler
		buttonAddBook.setOnAction(this::buttonAddBooksetOnAction);
		buttonDeleteBook = new Button("Delete Book");
		// add handler
		buttonDeleteBook.setOnAction(this::buttonDeleteBookSetOnAction);
		
		// set properties to these buttons
		BookStoreGUI.setButtonProperties(buttonAddBook);
		BookStoreGUI.setButtonProperties(buttonDeleteBook);
		
		// add them to the input box
		inputBox.getChildren().add(buttonAddBook);
		inputBox.getChildren().add(buttonDeleteBook);
		
		// on close request
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				hide();
				previousStage.show();
			}
		});
		
		// set the text to logout
		buttonLogout.setText("Logout");
	}
	
	// the handler for the delete book button
	private void buttonDeleteBookSetOnAction(ActionEvent actionEvent)
	{
		// get the selected book
		if( listViewBooks.getSelectionModel().getSelectedIndices().size() != 1 )
		{
			// show alert
			Alert alert = new Alert(AlertType.ERROR);
			// set context
			alert.setContentText("Select 1 book!");
			// show 
			alert.showAndWait();
			// return 
			return;
		}
		
		// remove this book
		bookStore.getBooksManagement().removeBook(listViewBooks.getSelectionModel().getSelectedIndex());
	
		// remove all items from list view
		listViewBooks.getItems().removeAll( listViewBooks.getItems() );
		
		// /show alert
		Alert alert = new Alert(AlertType.CONFIRMATION);
		// set context
		alert.setContentText("The selected book is deleted!");
		// show 
		alert.showAndWait();
	}
	
	// the handler for the add book button
	private void buttonAddBooksetOnAction(ActionEvent actionEvent)
	{
		// create a dialog
		Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Add Book");
        dialog.setHeaderText("Enter Book Details");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField textFieldAuthor = new TextField();
        TextField textFieldTitle = new TextField();
        TextField textFieldQuantity = new TextField("1");
        TextField textFieldPrice = new TextField();
        ObservableList<BookGenre> options =
            FXCollections.observableArrayList(BookGenre.values());
        ComboBox<BookGenre> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        dialogPane.setContent(new VBox(8, 
        		new HBox(4,new Label("Author:"),textFieldAuthor), 
        		new HBox(4,new Label("Title:"),textFieldTitle),
        		new HBox(4,new Label("Quantity:"),textFieldQuantity),
        		new HBox(4,new Label("Genre:"),comboBox),
        		new HBox(4,new Label("Price:"),textFieldPrice)));
        Platform.runLater(textFieldAuthor::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) 
            {
                return new Book(textFieldTitle.getText(),textFieldAuthor.getText(),1,
                		comboBox.getSelectionModel().getSelectedItem(), Double.parseDouble(textFieldPrice.getText()));
            }
            return null;
        });
        Optional<Book> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Book book) -> {
            // add to the book store
        		bookStore.getBooksManagement().addBook(book);
        });
	}
}
