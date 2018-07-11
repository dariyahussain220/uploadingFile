package com.upload.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.db.DBFactory;
import com.upload.models.FileInformation;

@Service
public class FileUploadService {

    // @Autowired
    // private ServletContext context;

    private final String UPLOADED_FOLDER = "/home/dariya/";

    @Autowired
    private DBFactory dbFactory;

    public int saveUploadedFile(MultipartFile file) {
        FileInformation fileInformation = new FileInformation();
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();

            try {
                // String destinationFile = context.getRealPath("WEB-INF/uploaded") +
                // File.separator + originalFilename;
                // file.transferTo(new File(destinationFile));
                fileInformation.setSize(file.getSize());
                fileInformation.setFileName(originalFilename);
                fileInformation.setFilepath("inside web-inf/uploaded");
                fileInformation.setDescription("Dummy Description");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                return dbFactory.getUploadFileDB().insert(fileInformation).getRowsAffected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    public List<FileInformation> getUploadedFiles() {
        return dbFactory.getUploadFileDB().results(FileInformation.class);
    }
}
