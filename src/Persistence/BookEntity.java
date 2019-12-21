package Persistence;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book", schema = "bookworm", catalog = "")
public class BookEntity {
    private int id;
    private String title;
    private String genre;
    private String author;
    private String isbn;
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    private int quantity;
    private Set<UserEntity> users = new HashSet<UserEntity>();

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "Author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "PublicationDate")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(author, that.author) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(publicationDate, that.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, author, isbn, publicationDate, quantity);
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "UserToBook",
            joinColumns = {@JoinColumn(name = "BookId")},
            inverseJoinColumns = {@JoinColumn(name = "UserId")}
    )
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
