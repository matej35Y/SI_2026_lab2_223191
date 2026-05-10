import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
    }

    @Test
    public void searchBookEveryStatementTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });

        List<Book> found = library.searchBookByTitle("Clean Code");
        assertNotNull(found);

        List<Book> notFound = library.searchBookByTitle("Harry Potter");
        assertNull(notFound);

        library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        List<Book> borrowed = library.searchBookByTitle("The Hobbit");
        assertNull(borrowed);
    }

    @Test
    public void borrowBookEveryBranchTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "J.R.R. Tolkien");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("The Hobbit", "");
        });

        assertDoesNotThrow(() -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });

        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });

        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Harry Potter", "J.K. Rowling");
        });



    }

    @Test
    public void searchBookMultipleConditionTest() {

        List<Book> result1 = library.searchBookByTitle("Clean Code");
        assertNotNull(result1);

        library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        List<Book> result2 = library.searchBookByTitle("The Hobbit");
        assertNull(result2);

        List<Book> result3 = library.searchBookByTitle("Harry Potter");
        assertNull(result3);
    }

    @Test
    public void borrowBookMultipleConditionTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "J.R.R. Tolkien");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("The Hobbit", "");
        });

        assertDoesNotThrow(() -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });
    }

}