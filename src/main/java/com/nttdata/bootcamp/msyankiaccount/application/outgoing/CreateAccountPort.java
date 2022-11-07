package com.nttdata.bootcamp.msyankiaccount.application.outgoing;

import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;

import reactor.core.publisher.Mono;

/**.*/
public interface CreateAccountPort {

  Mono<Account> createAccount(Account account);
  
}
