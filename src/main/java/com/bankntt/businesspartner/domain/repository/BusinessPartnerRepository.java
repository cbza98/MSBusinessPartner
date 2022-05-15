package com.bankntt.businesspartner.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankntt.businesspartner.domain.entity.BusinessPartner;

public interface BusinessPartnerRepository extends ReactiveMongoRepository<BusinessPartner, String> {
	
}
