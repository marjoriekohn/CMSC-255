package Labs.Lab9;

/**
 * Represents a book with a title and an author.
 */
public class Book {
	/**
	 * The title of the book.
	 */
	private String title;
	/**
	 * The author of the book.
	 */
	private String author;
	
	/**
	 * Constructs a new Book with a default title of "Test" and no author.
	 */
	public Book() {
		this.title = "Test";
		this.author = null;
	}
	
	/**
	 * Constructs a new Book with the specified title and author.
	 *
	 * @param title  the title of the book
	 * @param author the author of the book
	 */
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	/**
	 * Gets the title of the book.
	 *
	 * @return the title of the book
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of the book.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the author of the book.
	 *
	 * @return the author of the book
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author of the book.
	 *
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Gets the title and author of the book in the format:
	 * <code>"title" by author</code>.
	 *
	 * @return a string containing the book's title and author
	 */
	@Override
	public String toString() {
		return "\"" + title + "\" by " + author;
	}
}
