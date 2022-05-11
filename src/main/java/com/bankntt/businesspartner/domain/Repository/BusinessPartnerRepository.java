package com.bankntt.businesspartner.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankntt.businesspartner.domain.Entity.BusinessPartner;

public interface BusinessPartnerRepository extends ReactiveMongoRepository<BusinessPartner, String> {

}
