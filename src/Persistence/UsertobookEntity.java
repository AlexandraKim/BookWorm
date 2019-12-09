package Persistence;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "usertobook", schema = "bookworm", catalog = "")
public class UsertobookEntity {
    private int id;
    private String userid;
    private int bookId;
    private Date issueDate;
    private Date returnDate;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "BookId")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "IssueDate")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Basic
    @Column(name = "ReturnDate")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsertobookEntity that = (UsertobookEntity) o;
        return id == that.id &&
                bookId == that.bookId &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, bookId, issueDate, returnDate);
    }
}
