package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NoteMapper noteMapper;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void insertNotes(String notetitle,String notedescription,int userid)
    {
        noteMapper.insertNotes(new Notes(null,notetitle,notedescription,userid));
    }

    public List<NoteForm> getAllNotes(Integer userid)
    {
        return noteMapper.getAllNotes(userid);

    }

    public NoteForm getNoteById(Integer noteId)
    {
        return noteMapper.getNoteById(noteId);
    }

    public void updateNotes(Integer noteId,String notetitle,String noteDescription,Integer userId)
    {
        noteMapper.updateNotes(noteId,notetitle,noteDescription,userId);
    }

    public void deleteNoteById(Integer noteId)
    {
        noteMapper.deleteNoteById(noteId);
    }



}
