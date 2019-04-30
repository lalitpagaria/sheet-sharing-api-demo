package com.layer.demo.repository;

import com.layer.demo.domain.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    @Query("FROM Access s where s.sheet.sheetId = :sheetId and s.user.userId = :userId")
    Access findBySheetIdAndUserId(@Param("sheetId") Long sheetId, @Param("userId") Long userId);
}
