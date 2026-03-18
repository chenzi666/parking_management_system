package org.example.parking_management_system.controller;

import org.example.parking_management_system.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/upload/")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
}
