package com.hakg.boardv1.mapper;

import com.hakg.boardv1.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    
    @Select("SELECT * FROM board")
    List<Board> findAll();
    
    @Select("SELECT * FROM board WHERE id = #{id}")
    Board findById(Long id);
    
    @Insert("INSERT INTO board (title, content, writer, created_date, modified_date, view_count) " +
            "VALUES (#{title}, #{content}, #{writer}, NOW(), NOW(), 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Board board);
    
    @Update("UPDATE board SET title = #{title}, content = #{content}, " +
            "modified_date = NOW() WHERE id = #{id}")
    void update(Board board);
    
    @Delete("DELETE FROM board WHERE id = #{id}")
    void delete(Long id);
    
    @Update("UPDATE board SET view_count = view_count + 1 WHERE id = #{id}")
    void incrementViewCount(Long id);
    
    @Select("SELECT * FROM board ORDER BY id DESC LIMIT #{size} OFFSET #{offset}")
    List<Board> findAllWithPaging(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM board")
    int getTotalCount();
}
