package com.uniovi.sdi.grademanager.repositories;
import com.uniovi.sdi.grademanager.entities.*;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<User, Long>{
}