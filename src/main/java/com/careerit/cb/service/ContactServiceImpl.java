package com.careerit.cb.service;

import com.careerit.cb.domain.Contact;
import com.careerit.cb.dto.ContactDto;
import com.careerit.cb.util.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements  ContactService {

  @Autowired
  private StorageService storageService;
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Override
  public ContactDto addContact(ContactDto contactDto) {
    Assert.hasText(contactDto.getName(),"Name can't be null or empty");
    Assert.hasText(contactDto.getMobile(),"Mobile number can't be null or empty");
    Contact contact = objectMapper.convertValue(contactDto, Contact.class);
    contact = storageService.save(contact);
    contactDto = objectMapper.convertValue(contact, ContactDto.class);
    log.info("Contact is added with id :{}",contactDto.getId());
    return contactDto;
  }

  @Override
  public List<ContactDto> getContacts() {
      return storageService.getContacts()
          .stream()
          .map(c->objectMapper.convertValue(c,ContactDto.class))
          .collect(Collectors.toList());
  }

  @Override
  public List<ContactDto> search(String str) {
    return storageService.search(str)
        .stream()
        .map(c->objectMapper.convertValue(c,ContactDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public ContactDto getContactById(UUID id) {
    Contact contact = storageService.getContactById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact id"));
    log.info("Contact exists for the given id {}",contact.getId());
    return objectMapper.convertValue(contact,ContactDto.class);
  }

  @Override
  public ContactDto updateContact(ContactDto contactDto) {
    Assert.notNull(contactDto.getId(),"Id can't be null or empty");
    Assert.hasText(contactDto.getName(),"Name can't be null or empty");
    Assert.hasText(contactDto.getMobile(),"Mobile number can't be null or empty");
    Contact contact = objectMapper.convertValue(contactDto, Contact.class);
    contact = storageService.save(contact);
    contactDto = objectMapper.convertValue(contact, ContactDto.class);
    log.info("Contact with id {} updated successfully",contactDto.getId());
    return contactDto;
  }

  @Override
  public boolean deleteContact(UUID id) {
    return storageService.delete(id);
  }
}
