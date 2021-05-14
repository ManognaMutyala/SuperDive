package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private StorageService storageService;
    private SignUpService signUpService;
    private NotesService notesService;
    private CredentialService credentialService;


    public CredentialController(StorageService storageService, SignUpService signUpService, NotesService notesService, CredentialService credentialService) {
        this.storageService = storageService;
        this.signUpService = signUpService;
        this.notesService = notesService;
        this.credentialService = credentialService;
    }

    @PostMapping("/add")
    public String addCredentials(Model model, Authentication authentication, @ModelAttribute("noteForm") NoteForm noteFields, @ModelAttribute("CredentialsForm") CredentialsForm credentialsForm)
    {
        int userId= signUpService.getUserId(authentication.getName());
      //  if(credentialService.isExists(userId,credentialsForm.getCredentialId())==null)
        if(credentialsForm.getCredentialId()==null)
        {
            credentialService.insert(credentialsForm,userId);
        }
        else{

            credentialService.updateById(credentialsForm,userId);
        }
        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));
        return "home";
    }

    @GetMapping("/add")
    public String getCredentials(Model model, Authentication authentication, @ModelAttribute("noteForm") NoteForm noteFields, @ModelAttribute("CredentialsForm") CredentialsForm credentialsForm)
    {
        int userId= signUpService.getUserId(authentication.getName());
        //  if(credentialService.isExists(userId,credentialsForm.getCredentialId())==null)

        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));
        return "home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(Model model, Authentication authentication, @PathVariable("credentialId") int credentialId,@ModelAttribute("noteForm") NoteForm noteFields, @ModelAttribute("CredentialsForm") CredentialsForm credentialsForm){
        int userId=signUpService.getUserId(authentication.getName());
        if(credentialService.isExists(userId,credentialId)!=null)
        {
            credentialService.deleteCredentialById(userId,credentialId);
        }
        model.addAttribute("notes",notesService.getAllNotes(userId));
        model.addAttribute("fileList",storageService.getFiles(userId));
        model.addAttribute("credentialslist",credentialService.getAllCredentials(userId));

        return "home";
    }


}
