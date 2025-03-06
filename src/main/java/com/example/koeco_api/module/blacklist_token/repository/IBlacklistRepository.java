package com.example.koeco_api.module.blacklist_token.repository;

import com.example.koeco_api.module.blacklist_token.entity.BlacklistTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlacklistRepository extends JpaRepository<BlacklistTokenEntity, Long> {
    boolean existsAllByJwtId(String id);

    @Query("delete from BlacklistTokenEntity bl where bl.expired <:nowtime ")
    void deleteTokenExpired(@Param("nowtime")Long nowtime);
}
