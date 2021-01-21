import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class maintains the information about the books
 */
public class BooksManagement 
{
	// the array list of books
	private ArrayList<Book> books;
	
	// the constructor
	public BooksManagement(String inputFile)
	{
		// create the array list of books
		books = new ArrayList<>();
		
		// read the file
		readFile(inputFile);
	}
	
	// read the input file
	private void readFile(String inputFile)
	{
		// read all the books into the array list
		try
		{
			// create the scanner
			Scanner scanner = new Scanner(new File(inputFile));
			
			// load the file
			while( scanner.hasNextLine() )
			{
				// read the title
				String title = scanner.nextLine();
				
				// read the author
				String author = scanner.nextLine();
				
				// read the quantity
				int quantity = scanner.nextInt();
				scanner.nextLine();
				
				// read the genre
				String genre = scanner.nextLine();
				
				// read the price
				double price = scanner.nextDouble();
				if( scanner.hasNextLine())
					scanner.nextLine();
				
				// add this book to the list
				books.add(new Book(title, author, quantity, BookGenre.getEnum(genre), price));
			}
			
			// close the scanner
			scanner.close();
		}
		catch(FileNotFoundException fnfe)
		{
			// do nothing
		}
	}
	
	// method to add a book
	public void addBook(Book book)
	{
		// if this list of books contains this book 
		if( books.contains(book) )
		{
			// get this book
			int bookIndex = books.indexOf(book);
			
			// increment its quantity by given quantity
			books.get(bookIndex).setQuantity( books.get(bookIndex).getQuantity() + book.getQuantity() );
		}
		// else if the book is not found
		else
		{
			// add this book to the list
			books.add(book);
		}
	}
	
	// method to remove a book
	public boolean removeBook(Book book)
	{
		// if this list of books contains this book 
		if( books.contains(book) )
		{
			// decrement quantity
			
			// get this book
			int bookIndex = books.indexOf(book);
			
			// increment its quantity by given quantity
			books.get(bookIndex).setQuantity( books.get(bookIndex).getQuantity() - book.getQuantity() );
			
			// return true to indicate the book was removed
			return true;
		}
		// else if the book is not found
		else
		{
			// return false 
			return false;
		}
	}
	
	// method to remove a book
	public boolean removeBook(int index)
	{
		// check for the valid index
		if( index < 0 || index >= books.size() )
		{
			// return false for invalid index
			return false;
		}
		
		// remove this book
		books.remove(index);
		
		// return true
		return true;
	}
	
	// method to check if this book in stock
	public boolean inStock(Book book)
	{
		// // if this list of books contains this book 
		if( books.contains(book) )
		{
			// decrement quantity
			
			// get this book
			int bookIndex = books.indexOf(book);
			
			// check if the quantity of this books is enough
			return books.get(bookIndex).getQuantity() >= book.getQuantity();
		}
		// else if the book is not found
		else
		{
			// return false 
			return false;
		}
	}
	
	// method to search for books - by title
	public ArrayList<Book> searchByTitle(String title)
	{
		// the array list of books to return
		ArrayList<Book> foundBooks = new ArrayList<>();
		
		// look for all the books with the given title
		for( Book book : books )
		{
			// if this is the book we are looking for
			if( book.getTitle().equalsIgnoreCase(title))
			{
				// add this to the list of found books
				foundBooks.add(book);
			}
		}
		
		// return the list of books
		return foundBooks;
	}
	
	// method to search for books - by author
	public ArrayList<Book> searchByAuthor(String author)
	{
		// the array list of books to return
		ArrayList<Book> foundBooks = new ArrayList<>();
		
		// look for all the books with the given author
		for( Book book : books )
		{
			// if this is the book we are looking for
			if( book.getAuthor().equalsIgnoreCase(author))
			{
				// add this to the list of found books
				foundBooks.add(book);
			}
		}
		
		// return the list of books
		return foundBooks;
	}
	
	// method to search for books - by category
	public ArrayList<Book> searchByGenre(BookGenre bookGenre)
	{
		// the array list of books to return
		ArrayList<Book> foundBooks = new ArrayList<>();
		
		// look for all the books with the given book genre
		for( Book book : books )
		{
			// if this is the book we are looking for
			if( book.getBookGenre() == bookGenre )
			{
				// add this to the list of found books
				foundBooks.add(book);
			}
		}
		
		// return the list of books
		return foundBooks;
	}
	
	// method to list all the books
	public String toString()
	{
		// the string buffer to create the string
		StringBuffer buffer = new StringBuffer("");
		
		// add all the books
		
		// iterate over the list
		for( Book book : books )
		{
			// add this to the buffer
			buffer.append( book.toString() );
			
			// change a line
			buffer.append("\n");
		}
		
		// return the string
		return buffer.toString();
	}

	public ArrayList<Book> getBooks() {
		return books;
	}
}
