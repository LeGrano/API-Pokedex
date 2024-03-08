package fr.iut.sj.pkdxapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fr.iut.sj.pkdxapi.models.UserData;

public interface UserRepository extends MongoRepository<UserData,String>{
    @Query("{'login': ?0}")
    List<UserData> findByUsername(String username);
}