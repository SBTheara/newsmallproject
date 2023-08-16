package com.example.pos.Service;

import com.example.pos.entity.FileImageDB;
import com.example.pos.repository.FileImageDB_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.stream.Stream;
@Service
public class FileImgaDB_Service {
    @Autowired
    private FileImageDB_Repository fileImageDBRepository;

    public FileImageDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileImageDB FileDB = new FileImageDB(fileName,"image",file.getBytes());

        return fileImageDBRepository.save(FileDB);
    }
    public FileImageDB getFile(String id) {
        return fileImageDBRepository.findById(id).get();
    }
    public Stream<FileImageDB> getAllFiles() {
        return fileImageDBRepository.findAll().stream();
    }
}
