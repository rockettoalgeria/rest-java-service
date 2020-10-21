package com.example.springbootpostgresqlrest.controller;

import java.util.List;

import com.example.springbootpostgresqlrest.exception.ResourceNotFoundException;
import com.example.springbootpostgresqlrest.model.Contact;
import com.example.springbootpostgresqlrest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping("/")
	public String index() { return "Finally, it's working!"; }

	@GetMapping("/contact")
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@GetMapping("/contact={id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
		return ResponseEntity.ok().body(contact);
	}

	@PostMapping("/contact")
	public Contact createContact(@Validated @RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	@PutMapping("/contact={id}")
	public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long contactId,
												  @Validated @RequestBody Contact contactDetails) throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

		contact.setName(contactDetails.getName());
		final Contact updatedContact = contactRepository.save(contact);
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping("/contact={id}")
	public String deleteContact(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

		contactRepository.delete(contact);
		return "object with id <" + contactId + "> deleted";
	}
}
