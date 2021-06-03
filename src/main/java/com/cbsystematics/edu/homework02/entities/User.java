package com.cbsystematics.edu.homework02.entities;

import com.cbsystematics.edu.homework02.orm.Column;
import com.cbsystematics.edu.homework02.orm.Entity;
import com.cbsystematics.edu.homework02.orm.Table;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "z_users")
public class User extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String password;

    private String email;


    public User(Integer id, String firstName, String lastName, String username, String password, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{"
                + "id = " + id
                + ", firstName = " + firstName
                + ", lastName = " + lastName
                + ", username = " + username
                + ", password = " + password
                + ", email = " + email + "}";
    }
}
