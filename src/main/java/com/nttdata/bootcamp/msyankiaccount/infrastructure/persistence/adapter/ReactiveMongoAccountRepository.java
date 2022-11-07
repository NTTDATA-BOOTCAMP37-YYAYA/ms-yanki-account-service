package com.nttdata.bootcamp.msyankiaccount.infrastructure.persistence.adapter;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msyankiaccount.infrastructure.persistence.entity.AccountEntity;

import reactor.core.publisher.Mono;

/**.*/
public interface ReactiveMongoAccountRepository extends ReactiveMongoRepository
                                                        <AccountEntity, String> {
  

  @Query("{'$or':[ {'accountCelImei': ?0}, {'accountEmail' : ?1 }]}")
  public Mono<AccountEntity> findAccountByImeiEmail(String accountCelImei,String accountEmail);

}