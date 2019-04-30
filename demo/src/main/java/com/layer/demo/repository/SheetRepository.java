package com.layer.demo.repository;

import com.layer.demo.domain.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Long> {
    @Query("FROM Sheet s where s.file.fileId = :fileId and s.sheetName = :sheetName")
    Sheet findByFileIdAndSheetName(@Param("fileId") Long fileId, @Param("sheetName") String sheetName);
}
