package org.project.coursesmanagementsystem.repository;

import org.project.coursesmanagementsystem.model.admins.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository<Admins, Integer> {
}
