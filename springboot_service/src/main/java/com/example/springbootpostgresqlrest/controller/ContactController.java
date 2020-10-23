package com.example.springbootpostgresqlrest.controller;

import java.util.List;

import com.example.springbootpostgresqlrest.exception.ResourceNotFoundException;
import com.example.springbootpostgresqlrest.model.Contact;
import com.example.springbootpostgresqlrest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@GetMapping
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
		return ResponseEntity.ok().body(contact);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact createContact(@Validated @RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	@PutMapping("{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long contactId,
												  @Validated @RequestBody Contact contactDetails) throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

		contact.setName(contactDetails.getName());
		final Contact updatedContact = contactRepository.save(contact);
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping("{id}")
	public String deleteContact(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

		contactRepository.delete(contact);
		return "[deleted:" + contactId + "]";
	}
}
