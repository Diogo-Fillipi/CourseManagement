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

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Column(name = "admin_password")
    private String admin_password;
}
