package com.bankntt.businesspartner.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankntt.businesspartner.applicationHelpers.GenerateBusinessPartnerCode;
import com.bankntt.businesspartner.domain.Entity.BusinessPartner;
import com.bankntt.businesspartner.domain.Exception.EntityAlreadyExistsException;
import com.bankntt.businesspartner.domain.Exception.EntityNotExistsException;
import com.bankntt.businesspartner.domain.Repository.BusinessPartnerRepository;
import com.bankntt.businesspartner.infraestructure.Intefaces.IBusinessPartnerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessPartnerService implements IBusinessPartnerService {

	@Autowired(required = true)
	private BusinessPartnerRepository repository;

	@Override
	public Flux<BusinessPartner> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<BusinessPartner> save(BusinessPartner _request) {
		
		return repository.existsById(GenerateBusinessPartnerCode.generate(_request.getDocNum(), _request.getType())).flatMap(exists->{
		
								if(exists) {
									//System.out.print("BusinesPartner ya inscrito");
									return Mono.error(new EntityAlreadyExistsException());
								}
								
	    						BusinessPartner a =  BusinessPartner.builder().codeBP(GenerateBusinessPartnerCode.generate(_request.getDocNum(), _request.getType()))
	    											   .contactPerson(_request.getContactPerson())
	    											   .creditCard(_request.getCreditCard())
	    											   .creditCardLine(_request.getCreditCardLine())
	    											   .creditLine(_request.getCreditLine())
	    											   .debitCard(_request.getDebitCard())
	    											   .debitLine(_request.getDebitLine())
	    											   .docNum(_request.getDocNum())
	    											   .docType(_request.getDocType())
	    											   .email(_request.getEmail())
	    											   .name(_request.getName())
	    											   .telephone1(_request.getTelephone1())
	    											   .telephone2(_request.getTelephone2())
	    											   .type(_request.getType())
	    											   .valid(true)
	    											   .build();
	    							 return repository.save(a);
	    						
	    						
	    					});
		

	}

	@Override
	public Mono<BusinessPartner> delete(String Id) {
		return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)));
	}

	@Override
	public Mono<BusinessPartner> findById(String id) {
		
		return repository.existsById(id).flatMap(exists->{
			
			if(!exists) {
				//System.out.print("BusinesPartner ya inscrito");
				return Mono.error(new EntityNotExistsException());
			}
			return repository.findById(id);
		});
	}

	public Mono<ResponseEntity<BusinessPartner>> update(String id, BusinessPartner _request) {
		return repository.findById(id).flatMap(a -> {
			
			BusinessPartner.builder()
					   .contactPerson(_request.getContactPerson())
					   .creditCard(_request.getCreditCard())
					   .creditCardLine(_request.getCreditCardLine())
					   .creditLine(_request.getCreditLine())
					   .debitCard(_request.getDebitCard())
					   .debitLine(_request.getDebitLine())
					   .docNum(_request.getDocNum())
					   .docType(_request.getDocType())
					   .email(_request.getEmail())
					   .name(_request.getName())
					   .telephone1(_request.getTelephone1())
					   .telephone2(_request.getTelephone2())
					   .type(_request.getType())
					   .valid(_request.getValid())
					   .build();
							
			return repository.save(a);
		}).map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}
}
