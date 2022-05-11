package com.bankntt.businesspartner.application.Controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankntt.businesspartner.domain.Entity.BusinessPartner;
import com.bankntt.businesspartner.infraestructure.Services.BusinessPartnerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/BusinessPartner")
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
	public Mono<ResponseEntity<Map<String, Object>>> Create(@Valid @RequestBody Mono<BusinessPartner> request) {
		Map<String, Object> response = new HashMap<>();

		return request.flatMap(a -> {
			return service.save(a).map(c -> {
				response.put("BusinessPartner", c);
				response.put("mensaje", "Succesfull BusinessPartner Created");
				return ResponseEntity.created(URI.create("/api/BusinessPartner/".concat(c.getCodeBP())))
						.contentType(MediaType.APPLICATION_JSON).body(response);
			});
		});
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> Delete(@PathVariable String id) {
		return service.delete(id).map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
