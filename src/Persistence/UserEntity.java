package Persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", schema = "bookworm", catalog = "")
public class UserEntity {
    private String id;
    private int type;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isBlocked;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IsBlocked")
    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return type == that.type &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(isBlocked, that.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, firstName, lastName, password, isBlocked);
    }

    @ManyToMany
    @JoinTable(name = "UserToBook",
            joinColumns = {@JoinColumn(name = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "BookId")}
    )
    public Set<BookEntity> books = new HashSet<BookEntity>();
}
