package com.bankntt.businesspartner.infraestructure.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankntt.businesspartner.application.exception.classes.EntityAlreadyExistsException;
import com.bankntt.businesspartner.application.exception.classes.EntityNotExistsException;
import com.bankntt.businesspartner.application.helpers.GenerateBusinessPartnerCode;
import com.bankntt.businesspartner.domain.entity.BusinessPartner;
import com.bankntt.businesspartner.domain.repository.BusinessPartnerRepository;
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
	public Mono<BusinessPartner> save(BusinessPartner request) {
		
		String businessPartnerCode=GenerateBusinessPartnerCode.generate(request.getDocNum(), request.getType());
		
		return repository.existsById(businessPartnerCode)
						 .filter(exists->!exists)
						 .flatMap(r-> {
							   BusinessPartner businessPartner = BusinessPartner.builder()
							   .businessPartnerId(businessPartnerCode)
							   .contactPerson(request.getContactPerson())
							   .creditCard(request.getCreditCard())
							   .creditCardLine(request.getCreditCardLine())
							   .creditLine(request.getCreditLine())
							   .debitCard(request.getDebitCard())
							   .debitLine(request.getDebitLine())
							   .docNum(request.getDocNum())
							   .docType(request.getDocType())
							   .email(request.getEmail())
							   .name(request.getName())
							   .telephone1(request.getTelephone1())
							   .telephone2(request.getTelephone2())
							   .type(request.getType())
							   .valid(true)
							   .build();
							 return repository.save(businessPartner);
						 })
						 .switchIfEmpty(Mono.error(new EntityAlreadyExistsException()));
		

	}

	@Override
	public Mono<BusinessPartner> delete(String id) {
		return repository.findById(id)
						 .flatMap(deleted -> repository.delete(deleted)
						 .then(Mono.just(deleted))).switchIfEmpty(Mono.error(new EntityNotExistsException(BusinessPartner.class)));
	}

	@Override
	public Mono<BusinessPartner> findById(String id) {
		
		return repository.findById(id)
				.flatMap(bsp-> repository.findById(id))
				.switchIfEmpty(Mono.error(new EntityNotExistsException(BusinessPartner.class)));
		
	}

	public Mono<BusinessPartner> update( BusinessPartner request) {
		
		return repository.findById(request.getBusinessPartnerId()).flatMap(a -> {
			
			BusinessPartner businessPartner = BusinessPartner.builder()
					   .businessPartnerId(a.getBusinessPartnerId())
					   .contactPerson(request.getContactPerson())
					   .creditCard(request.getCreditCard())
					   .creditCardLine(request.getCreditCardLine())
					   .creditLine(request.getCreditLine())
					   .debitCard(request.getDebitCard())
					   .debitLine(request.getDebitLine())
					   .docNum(request.getDocNum())
					   .docType(request.getDocType())
					   .email(request.getEmail())
					   .name(request.getName())
					   .telephone1(request.getTelephone1())
					   .telephone2(request.getTelephone2())
					   .type(request.getType())
					   .valid(request.getValid())
					   .build();
					return repository.save(businessPartner);
			}).switchIfEmpty(Mono.error(new EntityNotExistsException(BusinessPartner.class)));
	}

	public Flux<BusinessPartner> saveAll(List<BusinessPartner> list ) {
		
		list.forEach(li->li.setBusinessPartnerId(GenerateBusinessPartnerCode.generate(li.getDocNum(), li.getType())));
		
		return repository.saveAll(list);
	}
}
