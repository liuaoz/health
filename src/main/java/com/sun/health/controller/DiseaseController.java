package com.sun.health.controller;

import com.sun.health.service.crawler.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disease/")
public class DiseaseController extends BaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("crawl")
    public ResponseEntity<String> crawl() {

        diseaseService.crawlDiseaseList();

        return ResponseEntity.ok("success");
    }

    @GetMapping("parse")
    public ResponseEntity<String> parseDiseases() {

        diseaseService.parseDiseases();

        return ResponseEntity.ok("success");
    }
}
