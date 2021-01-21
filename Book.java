
/*
 * The Book class
 * 
 */
public class Book 
{
	// the title of the book
	private String title;
	
	// the author of the book
	private String author;
	
	// the quantity of this book in the book store
	private int quantity;
	
	// the category of this book
	private BookGenre bookGenre;
	
	// the price
	private double price;

	// the constructor
	public Book(String title, String author, int quantity, BookGenre bookGenre, double price) {
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.bookGenre = bookGenre;
		this.price = price;
	}
	
	// the override equals method
	public boolean equals(Object o)
	{
		// if o is null, return false
		if( o == null )
			return false;
		
		// the o is an instance of Book
		if( o instanceof Book ) 
		{
			// get the book
			Book other = (Book)o;
			
			// two books are the same if they have the same, author, title and category
			return title.equalsIgnoreCase(other.title) &&
					author.equalsIgnoreCase(other.title) &&
					bookGenre == other.bookGenre;
		}
		// else return false
		else
			return false;
	}
	
	// the string representation of the book
	public String toString()
	{
		return "" + title + ", by " + author + " [$" + String.format("%.1f", price) + ", " + bookGenre + "]";
	}
	
	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BookGenre getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(BookGenre bookGenre) {
		this.bookGenre = bookGenre;
	}
	

	public double getPrice() {
		return price;
	}
	

	public void setPrice(double price) {
		this.price = price;
	}
}
