public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private double rating;
    private int publicationDate;

    public Book(String title, String author, double rating, int publicationDate) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.publicationDate = publicationDate;
    }

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", publicationDate=" + publicationDate +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return 0;
    }
}
