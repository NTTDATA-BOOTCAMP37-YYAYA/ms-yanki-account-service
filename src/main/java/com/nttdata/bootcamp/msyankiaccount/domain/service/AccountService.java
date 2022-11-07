package com.nttdata.bootcamp.msyankiaccount.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msyankiaccount.application.incoming.CreateAccountUseCase;
import com.nttdata.bootcamp.msyankiaccount.application.incoming.FindAccountByImeiEmailUseCase;
import com.nttdata.bootcamp.msyankiaccount.application.outgoing.CreateAccountPort;
import com.nttdata.bootcamp.msyankiaccount.application.outgoing.FindAccountByImeiEmailPort;
import com.nttdata.bootcamp.msyankiaccount.application.outgoing.SendBalancePort;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Balance;

import reactor.core.publisher.Mono;

/**.*/
@Service
public class AccountService implements CreateAccountUseCase,
                                       FindAccountByImeiEmailUseCase {

  final  Logger logger = LoggerFactory.getLogger(AccountService.class);
  
  @Autowired
  private CreateAccountPort createAccountPort;
  
  @Autowired
  private FindAccountByImeiEmailPort findAccountByImeiEmailPort;
  
  @Autowired
  private SendBalancePort sendBalancePort;
  

  @Override
  public Mono<Account> findAccountByImeiEmail(Account account) {
    return findAccountByImeiEmailPort.findAccountByImeiEmail(account);
  }
  @Override
  public Mono<Account> createAccount(Account account) {
    return createAccountPort.createAccount(account)
        .flatMap(newAccount -> {
          Balance balance = new Balance();
          balance.setAccountId(newAccount.getAccountId());
          balance.setBalanceAmmount(0);
          this.sendBalance(balance);
          return Mono.just(newAccount);
        });
  }


  /**.*/
  public Balance sendBalance(Balance balance) {
    if (balance != null) {
      logger.info("Send  balance {} ", balance);
      sendBalancePort.sendBalance(balance);
    }
    return balance;
  }

}
