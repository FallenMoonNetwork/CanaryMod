import java.util.ArrayList;
import java.util.List;

/**
 * Interface to books
 * @author Brian McCarthy
 *
 */
public class WrittenBook {

    OItemStack book;
    NBTTagCompound nbtTag;

    /**
     * Creates a new instance of WrittenBook.
     * Use {@link #isValid()} to make sure the item is a book. Theoretically it
     * is possible for any item to contain the correct tags that make it seem
     * like a book, although this does not occur "naturally".
     * @param item An instance of a book and quill or a written book.
     */
    public WrittenBook(Item item) {
        this(item.getBaseItem());
    }

    /**
     * Takes the OItemStack for this book.
     * @param book The book's OItemStack
     */
    public WrittenBook(OItemStack book) {
        this.book = book;
        if (book.d == null) {
            book.d(new ONBTTagCompound());
        }
        this.nbtTag = new NBTTagCompound(book.d);
    }

    /**
     * Get the title of this book.
     * @return empty string ("") if the <tt>NBTTagCompound</tt> is
     * <tt>null</tt> or if it does not contain the "title" tag (usually when
     * item is not a book)
     */
    public String getTitle() {
        return nbtTag.getString("title");
    }

    public void setTitle(String title) {
        nbtTag.removeTag("title");
        nbtTag.add("title", title);
    }

    /**
     * Get the author of this book.
     * @return empty string ("") if the <tt>NBTTagCompound</tt> is
     * <tt>null</tt> or if it does not contain the "author" tag (usually when
     * item is not a book)
     */
    public String getAuthor() {
        return nbtTag.getString("author");
    }

    /**
     * Set the author of this book.
     * @param author The new author
     */
    public void setAuthor(String author) {
        nbtTag.removeTag("author");
        nbtTag.add("author", author);
    }

    /**
     * Get the pages of this book.
     * @return A <tt>List&lt;String&gt;</tt> containing the pages, one page per
     * item.
     */
    public List<String> getPages() {
        List<String> pages = new ArrayList<String>();
        NBTTagList nbtPages = nbtTag.getNBTTagList("pages");
        for (int i = 0; i < nbtPages.size(); i++) {
            NBTTagString nbtPage = (NBTTagString) nbtPages.get(i);
            String page = nbtPage.getValue();
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
        NBTTagList nbtPages = new NBTTagList("pages");
        for (String page : pages) {
            nbtPages.add(new NBTTagString("", page));
        }
        nbtTag.removeTag("pages");
        nbtTag.add("pages", nbtPages);
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WrittenBook) {
            WrittenBook other = (WrittenBook) obj;
            return getTitle().equals(other.getTitle()) && getAuthor().equals(other.getAuthor()) && getPages().equals(other.getPages());
        }
        return false;
    }
}
