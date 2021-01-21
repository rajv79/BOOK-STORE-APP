import java.util.ArrayList;

public class User 
{
	// the user name for the user
	private String username;
	
	// the password
	private String password;
	
	// the list of the books purchased so far
	private ArrayList<Book> booksPurchased;

	// the constructor
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		
		// create the array list
		booksPurchased = new ArrayList<>();
	}

	// method to get the user-name
	public String getUsername() {
		return username;
	}

	// method to get the password
	public String getPassword() {
		return password;
	}
	
	// the method to login
	public boolean login(String password)
	{
		// the login is successful is the password matches
		return this.password.equals(password);
	}
	
	// method to purchase the books
	public void purchase(ArrayList<Book> books)
	{
		// add all the books
		for( Book book : books )
		{
			// if this book was already purchased
			if( booksPurchased.contains(book) )
			{
				// get this book
				int bookIndex = booksPurchased.indexOf(book);
				
				// add this book
				booksPurchased.get(bookIndex).setQuantity( booksPurchased.get(bookIndex).getQuantity() + book.getQuantity() );
			}
			// else if the book is not found
			else
			{
				// add this book to the list
				booksPurchased.add(book);
			}
		}
	}
	
	// the over ride equals method
	public boolean equals(Object o)
	{
		// if this is null, return false
		if( o == null )
			return false;
		
		// if o is an object of User
		if( o instanceof User )
		{
			// get other
			User other = (User)o;
			
			// compare the user-name
			return this.username.equals(other.username);
		}
		// else return false
		else
			return false;
	}
}
