package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private StorageService storageService;
    private SignUpService signUpService;
    private NotesService notesService;
    private CredentialService credentialService;

    public NotesController(StorageService storageService, SignUpService signUpService, NotesService notesService,CredentialService credentialService) {
        this.storageService = storageService;
        this.signUpService = signUpService;
        this.notesService = notesService;
        this.credentialService=credentialService;
    }

    @GetMapping("/upload")
    public String getNotes(Authentication authentication, Model model)
    {

      //  System.out.println("Inside get notes controller");
        int userId= signUpService.getUserId(authentication.getName());
        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));
        return "home";
    }

    @PostMapping("/upload")
    public String uploadNotes(@ModelAttribute("noteForm") NoteForm noteFields, @ModelAttribute("CredentialsForm") CredentialsForm credentialsForm, Authentication authentication, Model model)
    {
        int userId= signUpService.getUserId(authentication.getName());
        String notetitle=noteFields.getNoteTitle();
        String noteDescription=noteFields.getNoteDescription();

        if(notesService.getNoteById(noteFields.getNoteId()) != null)
        {
            notesService.updateNotes(noteFields.getNoteId(), notetitle, noteDescription, userId);
            model.addAttribute("notes",notesService.getAllNotes(userId));
            model.addAttribute("fileList",storageService.getFiles(userId));
            model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));
            return "home";
        }
        notesService.insertNotes(notetitle,noteDescription,userId);
        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));
        return "home";
    }

//    @PostMapping("/edit/{noteId}")
//    public String editNotes(@ModelAttribute("noteForm")NoteForm noteFields, @PathVariable("noteId") Integer noteId, Authentication authentication, Model model) {
//        System.out.println("Inside edit note controller");
//        int userId = signUpService.getUserId(authentication.getName());
//        String notetitle = noteFields.getNoteTitle();
//        String noteDescription = noteFields.getNoteDescription();
//        if (notesService.getNoteById(noteId) != null) {
//
//        }
//        return "home";
//    }

    @GetMapping("/delete/{noteId}")
    public String deleteNotes(@ModelAttribute("noteForm")NoteForm noteFields,@ModelAttribute("CredentialsForm") CredentialsForm credentialsForm,@PathVariable("noteId") Integer noteId,Authentication authentication,Model model){
        int userId= signUpService.getUserId(authentication.getName());
        if(notesService.getNoteById(noteFields.getNoteId()) != null)
        {
            notesService.deleteNoteById(noteFields.getNoteId());
        }
        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));

        return "home";
    }
}
