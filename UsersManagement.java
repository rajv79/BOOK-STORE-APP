import java.util.ArrayList;

/*
 * The class to manage all the users
 */
public class UsersManagement 
{
	// the list of the users
	private ArrayList<User> users;
	
	// the constructor
	public UsersManagement(String inputFile)
	{
		// create a list of users
		users = new ArrayList<>();
	}
	
	// method to add user
	public boolean addUser(User u)
	{
		// iterate and check if the user already exists with this user name
		for( User user : users )
		{
			// if the user name is found
			if( user.getUsername().equals(u.getUsername()))
			{
				// return false
				return false;
			}
		}
		// add this user
		users.add(u);
		
		// return true
		return true;
	}
	
	// method to remove a user
	public boolean remove(User u)
	{
		// if this list of users contains this user 
		if( users.contains(u) )
		{
			// decrement quantity
			
			// get this user
			int userIndex = users.indexOf(u);
			
			// remove this user
			users.remove(userIndex);
			
			// return true to indicate the user was removed
			return true;
		}
		// else if the book is not found
		else
		{
			// return false 
			return false;
		}
	}
	
	// method to list all the users
	public String toString()
	{
		// the string buffer to create the string
		StringBuffer buffer = new StringBuffer("");
		
		// add all the users
		
		// iterate over the list
		for( User user : users )
		{
			// add this to the buffer
			buffer.append( user.toString() );
			
			// change a line
			buffer.append("\n");
		}
		
		// return the string
		return buffer.toString();
	}
	
	// method to look for the user with the given user name
	public User getUser(String username)
	{
		// iterate over the list
		for( User user : users )
		{
			// if this is the user-name we are looking for
			if( user.getUsername().equals(username))
			{
				// return this user
				return user;
			}
		}
		
		// return null to indicate the user was not found
		return null;
	}
}
