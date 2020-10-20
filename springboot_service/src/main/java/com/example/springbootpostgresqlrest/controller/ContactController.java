package com.example.springbootpostgresqlrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<Contact> updateEmployee(@PathVariable(value = "id") Long employeeId,
												  @Validated @RequestBody Contact contactDetails) throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + employeeId));

		contact.setName(contactDetails.getName());
		final Contact updatedContact = contactRepository.save(contact);
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping("/contact={id}")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + employeeId));

		contactRepository.delete(contact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
