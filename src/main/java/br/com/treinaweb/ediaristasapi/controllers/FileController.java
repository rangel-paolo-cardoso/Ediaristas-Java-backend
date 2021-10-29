package br.com.treinaweb.ediaristasapi.controllers;

import java.io.IOException;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.treinaweb.ediaristasapi.services.FileService;

@Controller
@RequestMapping("/uploads")
public class FileController {
  
  @Autowired
  private FileService fileService;

  @GetMapping
  public ResponseEntity<Object> file(@RequestParam String fileName) throws IOException {
    var file = fileService.carregar(fileName);
    var contentType = URLConnection.guessContentTypeFromName(file.getFilename());

    return ResponseEntity.status(HttpStatus.OK)
      .header("content-Type", contentType)
      .body(file.getInputStream().readAllBytes());
  }

}
