package Labs.Lab9;

import java.util.ArrayList;

/**
 * Represents a bookshelf with a maximum size and a list of books.
 * @see Book
 */
public class Bookshelf {
	/**
	 * The maximum number of books the bookshelf can hold.
	 */
	private final int size;
	/**
	 * The list of books currently on the bookshelf.
	 */
	private ArrayList<Book> books;
	
	/**
	 * Constructs a new Bookshelf object with a size of 2 and an empty list of books.
	 */
	public Bookshelf() {
		this.size = 2;
		this.books = new ArrayList<>();
	}
	
	/**
	 * Constructs a new Bookshelf object with the specified size and an empty list of books.
	 *
	 * @param size the maximum number of books the bookshelf can hold
	 */
	public Bookshelf(int size) {
		this.size = size;
		this.books = new ArrayList<>(size);
	}
	
	/**
	 * Gets the size of the bookshelf.
	 *
	 * @return the maximum number of books the bookshelf can hold
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the list of books currently on the bookshelf.
	 *
	 * @return an ArrayList of books on the bookshelf
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Adds a book to the bookshelf if there is room.
	 *
	 * @param book the book to add to the bookshelf
	 */
	public void addBook(Book book) {
		if (books.size() < size) {
			books.add(book);
		}
	}
	
	/**
	 * Removes and returns the first book from the bookshelf.
	 *
	 * @return the removed book or null if the bookshelf is empty
	 */
	public Book removeBook() {
		if (books.isEmpty()) {
			return null;
		} else {
			return books.remove(0);
		}
	}
	
	/**
	 * Removes all books from the bookshelf.
	 */
	public void emptyBookshelf() {
		books = new ArrayList<>();
	}
}
