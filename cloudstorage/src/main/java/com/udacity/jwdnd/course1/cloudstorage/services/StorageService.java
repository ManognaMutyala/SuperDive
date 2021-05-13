package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StorageService {

    @Autowired
    FileMapper fileMapper;

    public int insert(MultipartFile multipartFile, int userid) throws IOException {
        File file=new File();
        file.setContenttype(multipartFile.getContentType());
        file.setFiledata(multipartFile.getBytes());
        file.setFilename(multipartFile.getOriginalFilename());
        file.setFilesize(String.valueOf(multipartFile.getSize()));
        file.setUserid(userid);

            return ( fileMapper.fileUpload(new File(null,file.getFilename(),file.getContenttype(),file.getFilesize(),file.getUserid(),file.getFiledata())));
    }

    public List<File> getFiles(int userid){
        return(fileMapper.getFilesList(userid));
    }

    public void deleteFile(String fileName){
      fileMapper.deleteFile(fileName);
    }

    public File getFileDetails(String fileid){
        return (fileMapper.getFileDetails(fileid));
    }

    public File getFileDetailsByName(String fileName){
        return (fileMapper.getFileDetailsByFileName(fileName));
    }
}
