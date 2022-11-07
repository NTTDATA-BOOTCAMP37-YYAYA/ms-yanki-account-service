package com.nttdata.bootcamp.msyankiaccount.application.incoming;

import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;

import reactor.core.publisher.Mono;

/**.*/
public interface FindAccountByImeiEmailUseCase {

  Mono<Account> findAccountByImeiEmail(Account account);
}
