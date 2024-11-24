package Labs.Lab9;

/**
 * Lab09 has the main method which creates Book objects and adds them to a Bookshelf object.
 *
 * @author Maggie Kohn
 * @version 2024.10.14
 * CMSC255.002
 * @see Book
 * @see Bookshelf
 */
public class Lab09 {
	public static void main(String[] args) {
		
		Book book1 = new Book("Into The Uncut Grass", "Trevor Noah");
		Book book2 = new Book("Unmasking Autism", "Devon Price, Ph.D.");
		Book book3 = new Book("Algorithms of Oppression", "Safiya Umoja Noble");
		
		System.out.println(book1);
		System.out.println(book2);
		System.out.println(book3);
		
		Bookshelf bookshelf = new Bookshelf(3);
		bookshelf.addBook(book1);
		bookshelf.addBook(book2);
		bookshelf.addBook(book3);
		
		System.out.println("\nBooks on the bookshelf:");
		for (Book book : bookshelf.getBooks()) {
			System.out.println(book);
		}
		
		bookshelf.emptyBookshelf();
		
		System.out.println("\nBooks on the bookshelf after emptying:");
		for (Book book : bookshelf.getBooks()) {
			System.out.println(book);
		}
	}
}
