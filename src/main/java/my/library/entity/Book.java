package my.library.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Book extends BaseEntity {

    private Genre genre;
    private String name;
    private String isbn;
    private Date date;
    private String description;
    List<Author> authorList = new ArrayList<>();

    public Book() {
        genre = new Genre();
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getId() + "/" + name + "/" + date + "/" + isbn + "/" + description + "/" + genre;
    }
}
