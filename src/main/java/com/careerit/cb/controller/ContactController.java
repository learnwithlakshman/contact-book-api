package com.careerit.cb.controller;

import com.careerit.cb.dto.ContactDto;
import com.careerit.cb.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @PostMapping
  @Operation(summary = "Add contact")
  public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contactDto) {
    return ResponseEntity.ok(contactService.addContact(contactDto));
  }

  @GetMapping("/all")
  @Operation(summary = "Get all contacts", description = "Get all contacts details")
  public ResponseEntity<List<ContactDto>> getContacts() {
    return ResponseEntity.ok(contactService.getContacts());
  }

  @PutMapping
  @Operation(summary = "Update contact")
  public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactDto) {
    return ResponseEntity.ok(contactService.updateContact(contactDto));
  }

  @GetMapping("/search")
  @Operation(summary = "Search contacts")
  public ResponseEntity<List<ContactDto>> getContacts(@RequestParam("str") String str) {
    return ResponseEntity.ok(contactService.search(str));
  }
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete contact by Id")
  public ResponseEntity<String> deleteContact(@PathVariable("id")UUID id){
      if(contactService.deleteContact(id)){
          return ResponseEntity.ok("Contact deleted successfully");
      }else{
        return ResponseEntity.badRequest().body("Contact is not found with given id");
      }
  }
  @GetMapping("/{id}")
  @Operation(summary = "Get contact by ID")
  public ResponseEntity<ContactDto> getContactById(@PathVariable("id") UUID id){
      return ResponseEntity.ok(contactService.getContactById(id));
  }

}
