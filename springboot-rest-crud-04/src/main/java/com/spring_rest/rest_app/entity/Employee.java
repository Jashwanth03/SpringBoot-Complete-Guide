package com.spring_rest.rest_app.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message="First name cannot be empty")
    @Column(nullable=false , length = 100)
    private String firstName;
    @Column(nullable=false , length = 100)
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable=false , length = 100 , unique = true)
    private String email;

    public Employee(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
