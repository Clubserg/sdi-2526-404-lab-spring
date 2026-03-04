package com.uniovi.sdi.grademanager.repositories;
import com.uniovi.sdi.grademanager.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query; // Importante añadir esto
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByDni(String dni);

    @Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(r.lastName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(r.dni) LIKE LOWER(CONCAT('%', :searchText, '%')))")
    Page<User> searchByNameLastNameAndDni(Pageable pageable, String searchText);

    Page<User> findAll(Pageable pageable);
}