public class BookStore 
{
	// the file names with the information on books and users
	private static final String BOOKS_INFO = "books.txt";
	private static final String USERS_INFO = "users.txt";
	
	// the object to manage the books
	private BooksManagement booksManagement;
	
	// the object to manage the users
	private UsersManagement usersManagement;
	
	// the constructor
	public BookStore()
	{
		// create both the objects
		booksManagement = new BooksManagement(BOOKS_INFO);
		usersManagement = new UsersManagement(USERS_INFO);
	}
	
	// try to login
	public boolean login(String username, String password)
	{
		// get the user with this user name
		User user = usersManagement.getUser(username);
		
		// if this is null, return false
		if( user == null )
			return false;
		
		// return the result of logging in
		return user.login(password);
	}
	
	// method to check if user names exists
	public boolean usernameExists(String username)
	{
		// check if this user-name exists
		return usersManagement.getUser(username) != null;
	}

	// getters
	public BooksManagement getBooksManagement() {
		return booksManagement;
	}

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}
}
