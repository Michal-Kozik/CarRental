package com.carrental.CarRental.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login=:login"),
        @NamedQuery(name = "User.findByLoginOrEmail", query = "SELECT u FROM User u WHERE u.login=:login OR u.email=:email"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
@Entity
public class User extends AbstractModel {
    @Column(unique = true)
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new LinkedList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserGroup> userGroups = new LinkedList<>();

    public User() { }

    public User(String login, String password, String email, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters.
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Methods.
    public void addGroup(String name) {
        for (UserGroup userGroup : userGroups) {
            if (userGroup.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        userGroups.add(new UserGroup(name, this));
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setUser(this);
    }

    public boolean hasAdminRole() {
        for (UserGroup userGroup : userGroups) {
            if (userGroup.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
