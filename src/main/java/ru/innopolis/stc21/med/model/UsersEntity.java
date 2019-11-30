package ru.innopolis.stc21.med.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="USERS")
public class UsersEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="is_doctor", nullable=false)
    private Boolean is_doctor;

    @Column(name="login", nullable=false, length=150)
    private String login;

    @Column(name="password", nullable=false, length=100)
    private String password;

    @Override
    public String toString() {
        return "id = " + this.id + " , login = " + this.login;
    }
}