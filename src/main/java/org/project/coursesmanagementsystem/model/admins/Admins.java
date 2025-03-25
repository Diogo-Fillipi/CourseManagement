package org.project.coursesmanagementsystem.model.admins;

import jakarta.persistence.*;

@Entity
@Table(name = "Admins")
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admin_id;

    @Column(name = "admin_email")
    private String admin_email;

    @Column(name = "admin_password")
    private String admin_password;
}
