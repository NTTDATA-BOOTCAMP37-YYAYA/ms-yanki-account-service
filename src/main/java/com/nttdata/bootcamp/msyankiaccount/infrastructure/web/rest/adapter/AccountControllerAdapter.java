package com.nttdata.bootcamp.msyankiaccount.infrastructure.web.rest.adapter;


import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msyankiaccount.application.incoming.CreateAccountUseCase;
import com.nttdata.bootcamp.msyankiaccount.application.incoming.FindAccountByImeiEmailUseCase;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;
import com.nttdata.bootcamp.msyankiaccount.infrastructure.web.rest.adapter.response.ResponseAccount;

import reactor.core.publisher.Mono;

/**.*/
@RestController
@RequestMapping("/yankiAccount")
public class AccountControllerAdapter {

  final Logger logger = LoggerFactory.getLogger(AccountControllerAdapter.class);
  
  @Autowired
  private  CreateAccountUseCase createAccountUseCase;
  @Autowired
  private  FindAccountByImeiEmailUseCase findAccountByNumberUseCase;


  /**.*/
  @PostMapping()
  public Mono<ResponseEntity<ResponseAccount>> createAccount(@RequestBody Account account) {
    //TODO
    return   findAccountByNumberUseCase.findAccountByImeiEmail(account)
    .flatMap(findAccount ->Mono.just(new ResponseEntity<ResponseAccount>(
               new ResponseAccount(HttpStatus.SC_NOT_FOUND, findAccount, "Account already exists"),
               null, HttpStatus.SC_NOT_FOUND)))
    .switchIfEmpty(createAccountUseCase.createAccount(account)
                    .flatMap(newAccount -> Mono.just(new ResponseEntity<ResponseAccount>(
                    new ResponseAccount(HttpStatus.SC_NOT_FOUND, newAccount, "Account has been created"),
                    null, HttpStatus.SC_NOT_FOUND))))
    .onErrorResume(e->Mono.just(new ResponseEntity<ResponseAccount>(
        new ResponseAccount(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
        null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));
  
  }
 
  /**.*/
  @GetMapping
  public Mono<ResponseEntity<ResponseAccount>> findAccountByImeiEmail(@RequestBody Account account ) {
    //TODO
    return findAccountByNumberUseCase.findAccountByImeiEmail(account)
        .flatMap(findAccount -> 
              Mono.just(new ResponseEntity<ResponseAccount>(
              new ResponseAccount(HttpStatus.SC_CREATED, findAccount, "Account found"),
              null, HttpStatus.SC_CREATED))
            ).onErrorResume(e->Mono.just(new ResponseEntity<ResponseAccount>(
                  new ResponseAccount(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
                  null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));

  }

}
