package com.nttdata.bootcamp.msyankiaccount.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msyankiaccount.application.outgoing.CreateAccountPort;
import com.nttdata.bootcamp.msyankiaccount.application.outgoing.FindAccountByImeiEmailPort;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;
import com.nttdata.bootcamp.msyankiaccount.infrastructure.persistence.entity.AccountEntity;

import reactor.core.publisher.Mono;

/**.*/
@Component
public class AccountRepositoryAdapter implements CreateAccountPort,
                                                 FindAccountByImeiEmailPort{

  @Autowired
  private ReactiveMongoAccountRepository reactiveMongodb;


  @Override
  public Mono<Account> createAccount(Account account) {
    return reactiveMongodb.insert(AccountEntity.toAccountEntity(account))
                                 .map(AccountEntity::toAccount);
  }

  @Override
  public Mono<Account> findAccountByImeiEmail(Account account) {
   
    return reactiveMongodb.findAccountByImeiEmail( account.getAccountEmail(), account.getAccountEmail())
                          .map(AccountEntity::toAccount);
  }


}
