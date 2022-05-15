package com.bankntt.businesspartner.application.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankntt.businesspartner.domain.entity.BusinessPartner;
import com.bankntt.businesspartner.infraestructure.Services.BusinessPartnerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/BusinessPartnerService/BusinessPartner")
public class BusinessPartnerController {
	
	@Autowired
	private BusinessPartnerService service;

	@GetMapping
	public Mono<ResponseEntity<Flux<BusinessPartner>>> FindAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<BusinessPartner> findById(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<BusinessPartner> request) {
		Map<String, Object> response = new HashMap<>();
							
							
		return request.flatMap(a -> {
			return service.save(a).map(c -> {
				response.put("BusinessPartner", c);
				response.put("mensaje", "Succesfull BusinessPartner Created");
				return ResponseEntity
						.created(URI.create("/api/BusinessPartner/".concat(c.getBusinessPartnerId())))
						.contentType(MediaType.APPLICATION_JSON).body(response);
			});
		});
	}
	
	@PutMapping
	public Mono<ResponseEntity<Map<String, Object>>> update(@Valid @RequestBody Mono<BusinessPartner> request) {
		Map<String, Object> response = new HashMap<>();
		
		return request.flatMap(a -> {
			return service.update(a).map(c -> {
				response.put("BusinessPartner", c);
				response.put("mensaje", "Succesfull BusinessPartner Updated");
				return ResponseEntity.ok()
					   .contentType(MediaType.APPLICATION_JSON)
					   .location( URI.create("/api/BusinessPartner/".concat(c.getBusinessPartnerId())))
					   .body(response);
			});
		});
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Map<String, Object>>> Delete(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		
		return service.delete(id)
				.map(c -> {
					response.put("BusinessPartner", c);
					response.put("mensaje", "Succesfull BusinessPartner Deleted");
					return ResponseEntity.ok()
							   .contentType(MediaType.APPLICATION_JSON)
							   .location( URI.create("/api/BusinessPartner/".concat(c.getBusinessPartnerId())))
							   .body(response);
				});
	}
	
	@PostMapping("/SaveAll")
	public Mono<ResponseEntity<Map<String, Object>>> saveBulk(@Valid @RequestBody Flux<BusinessPartner> businessPartnerList) {
		
		Map<String, Object> response = new HashMap<>();
				
		return businessPartnerList.collectList()
				.flatMap(a -> service.saveAll(a).collectList())
				.map(c -> {
					response.put("BusinessPartners", c);
					response.put("mensaje", "Succesfull BusinessPartner Created");
					return ResponseEntity
							.created(URI.create("/api/BusinessPartner/"))
							.contentType(MediaType.APPLICATION_JSON).body(response);
				});
	}

}
