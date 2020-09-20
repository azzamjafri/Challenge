package com.atlan.challenge.storage_service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.atlan.challenge.model.Model;
import com.atlan.challenge.repo.Repository;

@Service
public class StorageService {

    
    

    private Repository repository;

    public Model storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);

            }

            Model dbFile = new Model(fileName, file.getContentType(), file.getBytes());

            return repository.save(dbFile);
        
        }catch(IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    

    public Model getFile(String fileId) {
        return repository.findById(fileId)
                .orElseThrow(() -> MyFileNotFoundException("File not found with id " + fileId));
    }
}