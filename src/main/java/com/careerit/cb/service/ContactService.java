package com.careerit.cb.service;

import com.careerit.cb.dto.ContactDto;

import java.util.List;
import java.util.UUID;

public interface ContactService {

      ContactDto addContact(ContactDto contactDto);
      List<ContactDto> getContacts();
      List<ContactDto> search(String str);
      ContactDto getContactById(UUID id);
      ContactDto updateContact(ContactDto contactDto);
      boolean deleteContact(UUID id);
}
