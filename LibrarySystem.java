class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.bookId = generateBookId();
        totalBooks++;
        availableBooks++;
    }

    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println("Book issued: " + bookId);
        } else {
            System.out.println("Book not available: " + bookId);
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println("Book returned: " + bookId);
        } else {
            System.out.println("Book was not issued: " + bookId);
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Available : " + isAvailable);
        System.out.println("-----------------------------");
    }

    public static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int memberCounter = 0;

    public Member(String memberName) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable() && bookCount < booksIssued.length) {
            book.issueBook();
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
            System.out.println(memberName + " borrowed book " + book.getBookId());
        } else {
            System.out.println("Cannot borrow book.");
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        System.out.println(memberName + " returned book " + bookId);
                        booksIssued[i] = booksIssued[bookCount - 1];
                        booksIssued[bookCount - 1] = null;
                        bookCount--;
                        return;
                    }
                }
            }
        }
        System.out.println("Book ID " + bookId + " not found in member's records.");
    }

    public static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }

    public void displayMemberInfo() {
        System.out.println("Member ID   : " + memberId);
        System.out.println("Member Name : " + memberName);
        System.out.print("Books Issued: ");
        for (int i = 0; i < bookCount; i++) {
            System.out.print(booksIssued[i] + " ");
        }
        System.out.println("\n-----------------------------");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("Java Programming", "James Gosling");
        books[1] = new Book("Data Structures", "Robert Lafore");
        books[2] = new Book("AI Basics", "Stuart Russell");

        Member[] members = new Member[2];
        members[0] = new Member("Alice");
        members[1] = new Member("Bob");

        members[0].borrowBook(books[0]);
        members[1].borrowBook(books[1]);
        members[0].borrowBook(books[2]);
        members[0].returnBook("B001", books);

        for (Book b : books) {
            b.displayBookInfo();
        }
        for (Member m : members) {
            m.displayMemberInfo();
        }

        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
