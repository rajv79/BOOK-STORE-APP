
/*
 * The enum to store the category of the books.
 */
public enum BookGenre 
{
	// add the categories
	ADVENTURE("ADVENTURE"),
	SCIENCE_FICTION("SCIENCE_FICTION"),
	HORROR("HORROR"),
	ART("ART"),
	HISTORY("HISTORY"),
	HUMOR("HUMOR"),
	MOTIVATIONAL("MOTIVATIONAL"),
	UNKNOWN("UNKNOWN");
	
	// the constructor
	private BookGenre(String genre)
	{
		this.genre = genre;
	}
	
	public String toString()
	{
		return genre;
	}
	
	// the private String genre
	private String genre;
	
	// get the enum based on string
	public static BookGenre getEnum(String genre)
	{
		// the genre to return
		BookGenre status = UNKNOWN;
		
		// iterate and check
		for( BookGenre bookGenre : values() )
		{
			// if this is what we are looking for
			if( genre.equalsIgnoreCase(bookGenre.genre))
				return bookGenre;
		}
		
		// return status
		return status;
	}
}
