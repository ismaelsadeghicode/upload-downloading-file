package com.javatar.uploaddownloading.controller;

import com.javatar.uploaddownloading.services.ImageServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageServic servic;
    @Value("${image.path}")
    private String path;

    @PostMapping
    ResponseEntity saveImage(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(servic.saveImage(file));
    }

    @GetMapping
    ResponseEntity<List<String>> findAllImageUrl(){
        return ResponseEntity.ok(servic.findAllImageUrl());
    }

    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] downloaderImage(@PathVariable String imageName) throws Exception {
        return servic.downloaderImage(imageName);
    }
}
