package com.careerit.cb.util;

import com.careerit.cb.domain.Contact;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorageService {

  private final Map<UUID, Contact> map;

  public StorageService() {
    map = new HashMap<>();
  }

  public Contact save(Contact contact) {
    if (contact.getId() == null) {
      UUID id = UUID.randomUUID();
      contact.setId(id);
      map.put(contact.getId(), contact);
    } else {
      map.put(contact.getId(), contact);
    }
    return contact;
  }

  public List<Contact> getContacts() {
    return new ArrayList<>(map.values());
  }

  public Optional<Contact> getContactById(UUID id) {
    return Optional.of(map.get(id));
  }

  public boolean delete(UUID id) {
    return map.remove(id) != null;
  }

  public List<Contact> search(String str) {
    return getContacts().stream()
        .filter(c -> c.getName().toLowerCase().contains(str.toLowerCase()) || c.getMobile().contains(str) || c.getCity().toLowerCase().contains(str.toLowerCase()))
        .collect(Collectors.toList());
  }

  public Optional<Contact> getContactByMobile(String mobile){
      // Logic
     return Optional.empty();
  }
}
