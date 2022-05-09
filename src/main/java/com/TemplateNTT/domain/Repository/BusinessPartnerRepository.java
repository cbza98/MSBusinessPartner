package com.TemplateNTT.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.TemplateNTT.domain.Entity.BusinessPartner;

public interface BusinessPartnerRepository extends ReactiveMongoRepository<BusinessPartner, String> {

}
