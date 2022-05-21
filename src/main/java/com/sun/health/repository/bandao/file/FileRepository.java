package com.sun.health.repository.bandao.file;

import com.sun.health.entity.bandao.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query(value = "select id,create_time,creator,operator,remark,update_time,file_name,file_type,hash,size,uuid,category from tb_file where category = ?1",nativeQuery = true)
    List<Map<String,Object>> findSimpleByFileCategory(String fileCategory);
}
