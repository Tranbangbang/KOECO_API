package com.example.koeco_api.module.exhibition.repository;

import com.example.koeco_api.module.exhibition.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    boolean existsByNameKr(String name);
    boolean existsByNameEn(String name);
    boolean existsByNameCn(String name);

    @Query("""
            select case when count (ctg)> 0 then true else false end 
            from Category ctg
            where ctg.id != :id and ctg.nameKr = :cate_name
            """)
    boolean existsByNameKrUpdate(@Param("cate_name") String cate_name,
                                 @Param("id") Long id);

    @Query("""
            select case when count (ctg)> 0 then true else false end 
            from Category ctg
            where ctg.id != :id and ctg.nameEn = :cate_name
            """)
    boolean existsByNameEnUpdate(@Param("cate_name") String cate_name,
                                 @Param("id") Long id);

    @Query("""
            select case when count (ctg)> 0 then true else false end 
            from Category ctg
            where ctg.id != :id and ctg.nameCn = :cate_name
            """)
    boolean existsByNameCnUpdate(@Param("cate_name") String cate_name,
                                 @Param("id") Long id);

    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
}
