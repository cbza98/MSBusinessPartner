package com.bankntt.businesspartner.infraestructure.Intefaces;

import com.bankntt.businesspartner.domain.Entity.BusinessPartner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBusinessPartnerService {
	public Flux<BusinessPartner> findAll();

	public Mono<BusinessPartner> save(BusinessPartner _account);

	public Mono<BusinessPartner> findById(String Id);

	public Mono<BusinessPartner> delete(String Id);
}
