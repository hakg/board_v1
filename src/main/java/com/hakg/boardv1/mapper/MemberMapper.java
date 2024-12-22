package com.hakg.boardv1.mapper;

import com.hakg.boardv1.domain.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    
    @Insert("INSERT INTO member (username, password, email, name, role, created_date, modified_date) " +
            "VALUES (#{username}, #{password}, #{email}, #{name}, #{role}, #{createdDate}, #{modifiedDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Member member);

    @Select("SELECT * FROM member WHERE username = #{username}")
    Member findByUsername(String username);

    @Select("SELECT * FROM member WHERE email = #{email}")
    Member findByEmail(String email);

    @Update("UPDATE member SET last_login_date = #{lastLoginDate} WHERE id = #{id}")
    void updateLastLoginDate(Member member);
}
