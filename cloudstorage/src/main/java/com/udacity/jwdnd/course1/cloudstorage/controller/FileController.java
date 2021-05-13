package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/file")
@Controller
public class FileController {

    @Autowired
    StorageService storageService;

    @Autowired
    SignUpService signUpService;

    private NotesService notesService;


    public FileController(StorageService storageService, SignUpService signUpService,NotesService notesService) {
        this.storageService = storageService;
        this.signUpService = signUpService;
        this.notesService=notesService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model, Authentication authentication,@ModelAttribute("noteForm") NoteForm noteForm) throws IOException {
        int userId=signUpService.getUserId(authentication.getName());
        if(storageService.getFileDetailsByName(multipartFile.getOriginalFilename())!=null) {
            model.addAttribute("uploadError",true);

            String username=authentication.getName();
            List<File> files =new ArrayList<File>();
            files=storageService.getFiles(userId);
            model.addAttribute("fileList",files);
            model.addAttribute("notes",notesService.getAllNotes(userId));
            return "home";
        }
        storageService.insert(multipartFile,userId);
        String username=authentication.getName();
        List<File> files =new ArrayList<File>();
        files=storageService.getFiles(userId);
        model.addAttribute("fileList",files);
        model.addAttribute("notes",notesService.getAllNotes(userId));

        return "home";
    }

    @GetMapping("/{fileId}")
    public ResponseEntity viewFiles(@PathVariable("fileId") String fileid, Authentication authentication,  HttpServletResponse response, Model model) throws IOException {
        int userId=signUpService.getUserId(authentication.getName());
        File file=storageService.getFileDetails(fileid);
        if(file.getUserid()==userId)
        {
            String filename=file.getFilename();
            MediaType mediaType = MediaType.parseMediaType(file.getContenttype());
            String contentDisposition = "Content-Disposition: attachment; filename=\"" + filename + "\"";
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(file.getFiledata());

        }
        model.addAttribute("notes",notesService.getAllNotes(userId));

        response.sendRedirect("/home");

        return null;

    }

    @GetMapping("/delete/{filename}")
    public ResponseEntity deleteFile(@PathVariable("filename") String fileName,Authentication authentication,HttpServletResponse servletResponse,Model model) throws IOException {
        int userId= signUpService.getUserId(authentication.getName());
        File file=storageService.getFileDetailsByName(fileName);
        System.out.println("filename inside delete controller is "+ fileName);
        if(file.getUserid()==userId)
        {
            storageService.deleteFile(fileName);
        }
        model.addAttribute("notes",notesService.getAllNotes(userId));

        servletResponse.sendRedirect("/home");

        return null;

    }


}
