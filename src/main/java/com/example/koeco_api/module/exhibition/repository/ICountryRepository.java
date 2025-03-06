package com.example.koeco_api.module.exhibition.repository;

import com.example.koeco_api.module.exhibition.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

    @Query("""
            select ctr from Country ctr 
            where 
                ctr.nameKr = :name
                or ctr.nameEn = :name
                or ctr.nameCn = :name
            """)
    boolean checkExitsByName(@Param("name")String name);
    boolean existsByNameKr(String nameKr);
    boolean existsByNameEn(String nameEn);
    boolean existsByNameCn(String nameCh);

    Optional<Country> findById(Long id);

    List<Country> findAll();
    Page<Country> findAll(Pageable pageable);
}
