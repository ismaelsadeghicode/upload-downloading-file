package com.javatar.uploaddownloading.controller;

import com.javatar.uploaddownloading.domain.Image;
import com.javatar.uploaddownloading.services.ImageServic;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    ResponseEntity<Image> saveImage(@RequestParam("file") MultipartFile file,
                                    @RequestParam("description") String description) {
        return ResponseEntity.ok(servic.saveImage(file, description));
    }

    @GetMapping
    ResponseEntity<List<Image>> findAllImageUrl() {
        return ResponseEntity.ok(servic.findAllImageUrl());
    }

    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] downloaderImage(@PathVariable String imageName) throws Exception {
        return servic.downloaderImage(imageName);
    }
}
