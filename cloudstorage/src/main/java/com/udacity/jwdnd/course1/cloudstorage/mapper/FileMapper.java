package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("insert into FILES(filename,contenttype,filesize,userid,filedata )values(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    int fileUpload(File file);

    @Select("select * from FILES where userid=#{userid}")
    List<File> getFilesList(int userid);

    @Select("select * from files where fileid=#{fileid}")
    File getFileDetails(String fileid);

    @Select("select * from files where filename= #{fileName}")
    File getFileDetailsByFileName(String fileid);

    @Delete("delete from files where filename= #{fileName}")
    void deleteFile(String fileName);
}
