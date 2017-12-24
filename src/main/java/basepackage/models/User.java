package basepackage.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,
                getterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {

    @Id
    @Column
    @JsonProperty
    private String login;

    @Column
    @JsonProperty
    private String password;

    @Column(name = "auth_type")
    @JsonProperty
    private String type;

    @Column
    @JsonProperty
    private String role;

    @Column(name = "avatar_id")
    @JsonProperty
    private String avatar;

    @Column(name = "user_name")
    @JsonProperty
    private String name;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Site> sites;

    @Column
    @JsonProperty
    private  String number;

    @Column
    @JsonProperty
    private  String location;

    @Column
    @JsonProperty
    private  String email;

    @Column
    @JsonProperty
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", type='" + type + '\'' +
                ", role='" + role + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
