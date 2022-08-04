package com.mailorderpharmacy.authservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharmacy.authservice.entity.UserData;


// JPA Repository to interacts with database
@Repository
public interface UserDAO extends JpaRepository<UserData, String> {

}
