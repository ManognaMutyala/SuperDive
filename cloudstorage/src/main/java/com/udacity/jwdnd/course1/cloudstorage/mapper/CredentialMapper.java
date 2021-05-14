package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
/* Mapper to perform database operations with credentials */
public interface CredentialMapper {

    @Insert("insert into CREDENTIALS(url,username,key,password,userid) values (#{url},#{username},#{key}, #{password},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "credentialid")
    int insert(Credentials credentials);

    @Select("select * from CREDENTIALS where userid=#{userid} and credentialid=#{credentialid}")
    Credentials isExists(int userid,int credentialid);

    @Select("select * from CREDENTIALS where userid=#{userid} ")
    List<Credentials> getAllCredentials(int userid);

    @Delete("delete from CREDENTIALS where userid=#{userid} and credentialid=#{credentialid}")
    void deleteCredentialById(int userid,int credentialid);

    @Update("UPDATE CREDENTIALS SET url=#{url},password=#{password},username=#{username} WHERE userid=#{userid} and credentialid=#{credentialid}")
    void update(String url,String username,String password,int userid,int credentialid);

    @Select("SELECT KEY FROM CREDENTIALS WHERE credentialid=#{credentialid} and userid=#{userid}")
    String getKey(int credentialid,int userid);
}
