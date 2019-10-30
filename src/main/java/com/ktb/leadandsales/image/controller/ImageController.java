package com.ktb.leadandsales.image.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {
    @GetMapping(value = "/images/{name}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<InputStreamResource> getImage(@PathVariable(value  = "name") String name) throws IOException {
        System.out.println("name = " + name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ClassPathResource("static/image/"+name+".png").getInputStream()));
    }
}
