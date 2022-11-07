package com.nttdata.bootcamp.msyankiaccount.application.outgoing;

import com.nttdata.bootcamp.msyankiaccount.domain.model.Balance;

/**.*/
public interface SendBalancePort {

  void sendBalance(Balance balance);
}
