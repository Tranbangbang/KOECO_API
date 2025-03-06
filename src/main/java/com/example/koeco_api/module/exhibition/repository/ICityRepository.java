package com.example.koeco_api.module.exhibition.repository;

import com.example.koeco_api.module.exhibition.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);

    @Query("""
            select case when count (ct)> 0 then true else false end 
            from City ct 
            where ct.country.nameKr = :country_name 
            and ct.nameKr = :city_name
            """)
    boolean existsByCountryAndNameKr(@Param("country_name") String country,
                                     @Param("city_name") String city_name);

    @Query("""
            select case when count (ct)> 0 then true else false end 
            from City ct 
            where ct.country.nameKr = :country_name 
            and ct.nameEn = :city_name
            """)
    boolean existsByCountryAndNameEn(@Param("country_name") String country,
                                     @Param("city_name") String city_name);

    @Query("""
            select case when count (ct)> 0 then true else false end 
            from City ct 
            where ct.country.nameKr = :country_name 
            and ct.nameCn = :city_name
            """)
    boolean existsByCountryAndNameCn(@Param("country_name") String country,
                                     @Param("city_name") String city_name);
}
