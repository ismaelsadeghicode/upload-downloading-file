package com.javatar.uploaddownloading.services;

import com.javatar.uploaddownloading.domain.Image;
import com.javatar.uploaddownloading.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServic {

    @Value("${image.path}")
    private String path;
    @Value("${image.url}")
    private String url;
    @Autowired
    private ImageRepository repository;

    public String saveImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path pathImage = Paths.get(path + file.getOriginalFilename());
            Files.write(pathImage, bytes);
            file.getOriginalFilename();
            Image image = Image.builder()
                    .name(file.getOriginalFilename())
                    .description("")
                    .path(path)
                    .build();
            repository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url + file.getOriginalFilename();
    }

    public List<String> findAllImageUrl() {
        List<String> imageUrls = new ArrayList<>();
        List<Image> images = repository.findAll();
        for (Image image : images) {
            imageUrls.add( url + image.getName());
        }
        return imageUrls;
    }

    public byte[] downloaderImage(String imageName) throws Exception {
        InputStream in = new FileInputStream(path + imageName);
        return IOUtils.toByteArray(in);
    }
}
