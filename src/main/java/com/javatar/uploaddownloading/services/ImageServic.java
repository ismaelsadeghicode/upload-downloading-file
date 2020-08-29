package com.javatar.uploaddownloading.services;

import com.javatar.uploaddownloading.domain.Image;
import com.javatar.uploaddownloading.repository.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageServic {

    @Value("${image.path}")
    private String path;
    @Value("${image.url}")
    private String url;
    @Autowired
    private ImageRepository repository;

    public Image saveImage(MultipartFile file, String description) {
        try {
            byte[] bytes = file.getBytes();
            Path pathImage = Paths.get(path + file.getOriginalFilename());
            Files.write(pathImage, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.getOriginalFilename();
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .description(description)
                .path(path)
                .url(url + file.getOriginalFilename())
                .build();
        repository.save(image);
        return image;
    }

    public List<Image> findAllImageUrl() {
        return repository.findAll();
    }

    public byte[] downloaderImage(String imageName) throws Exception {
        InputStream in = new FileInputStream(path + imageName);
        return IOUtils.toByteArray(in);
    }
}
