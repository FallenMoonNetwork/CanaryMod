import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interface to books
 * @author Brian McCarthy
 *
 */
public class WrittenBook {

    OItemStack book;

    /**
     * Creates a new instance of WrittenBook.
     * Use {@link isValid()} to make sure the item is a book. Theoretically it
     * is possible for any item to contain the correct tags that make it seem
     * like a book, although this does not occur "naturally".
     * @param item An instance of a book and quill or a written book.
     */
    public WrittenBook(Item item) {
        this.book = item.getBaseItem();
    }

    /**
     * Takes the OItemStack for this book.
     * @param book The book's OItemStack
     */
    public WrittenBook(OItemStack book) {
        this.book = book;
    }

    /**
     * Get the title of this book.
     * @return empty string ("") if the <tt>NBTTagCompound</tt> is
     * <tt>null</tt> or if it does not contain the "title" tag (usually when
     * item is not a book)
     */
    public String getTitle() {
        if (book.d == null) {
            return "";
        }
        if (!book.d.b("title")) {
            return "";
        }
        return book.d.i("title");
    }

    public void setTitle(String title) {
    	if (book.d == null) {
        	book.d = new ONBTTagCompound("tag");
        }
        book.d.a("title", title);
    }

    /**
     * Get the author of this book.
     * @return empty string ("") if the <tt>NBTTagCompound</tt> is
     * <tt>null</tt> or if it does not contain the "author" tag (usually when
     * item is not a book)
     */
    public String getAuthor() {
        if (book.d == null) {
            return "";
        }
        if (!book.d.b("author")) {
            return "";
        }
        return book.d.i("author");
    }

    /**
     * Set the author of this book.
     * @param author The new author
     */
    public void setAuthor(String author) {
    	if (book.d == null) {
        	book.d = new ONBTTagCompound("tag");
        }
        book.d.a("author", author);
    }

    /**
     * Get the pages of this book.
     * @return A <tt>List&lt;String&gt;</tt> containing the pages, one page per
     * item.
     */
    public List<String> getPages() {
        if (book.d == null) {
            return Collections.emptyList();
        }
        List<String> pages = new ArrayList<String>();
        ONBTTagList nbtPages = (ONBTTagList) book.d.a("pages");
        for (int i = 0; i < nbtPages.c(); i++) {
            ONBTTagString nbtPage = (ONBTTagString) nbtPages.b(i);
            String page = nbtPage.a;
            if (page != null && !page.trim().equals("")) {
                pages.add(page);
            }
        }
        return pages;
    }

    /**
     * Set the pages of this book.
     * @param pages A list containing the new pages as a <tt>String</tt>
     */
    public void setPages(List<String> pages) {
        if (book.d == null) {
        	book.d = new ONBTTagCompound("tag");
        }
    	ONBTTagList nbtPages = new ONBTTagList("pages");
        for (String page : pages) {
            nbtPages.a(new ONBTTagString("", page));
        }
        book.d.a("pages", nbtPages);
    }

    /**
     * Check if this book is valid.
     * @return <tt>true</tt> when this is really a book, <tt>false</tt> otherwise.
     */
    public boolean isValid() {
        if (book.c == 386 || book.c == 387) {
            return true;
        }
        return false;
    }

}
