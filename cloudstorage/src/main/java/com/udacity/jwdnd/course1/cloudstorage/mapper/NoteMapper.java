package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("insert into notes(notetitle,notedescription,userid) values(#{notetitle},#{notedescription},#{userid})")
            int insertNotes (Notes notes);

    @Select("select * from notes where userId=#{userId}")
    List<NoteForm> getAllNotes(Integer userId);

    @Select("select * from notes where noteId=#{noteId}")
    NoteForm getNoteById(Integer noteId);

    @Update("update notes set notetitle=#{notetitle}, notedescription=#{notedescription} where noteid=#{noteid} and userid=#{userid}")
    void updateNotes(Integer noteid,String notetitle,String notedescription,Integer userid);

    @Delete("delete from notes where noteid=#{noteid}")
    void deleteNoteById(Integer noteid);
}
