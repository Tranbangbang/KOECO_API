package com.example.koeco_api.module.user.repo;

import com.example.koeco_api.module.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userNumber);

    Boolean existsByEmail(String email);

    @Query("""
            select distinct us from User us
            join us.userLocalization usLocal  
            where 
            (:username is null or us.userId = :username)
            and (:company is null or  usLocal.companyName = :company)
            and (:director is null or usLocal.director = :director)
            """)
    Page<User> findUser(Pageable pageable,
                        @Param("username") String username,
                        @Param("company") String company,
                        @Param("director") String director);
}