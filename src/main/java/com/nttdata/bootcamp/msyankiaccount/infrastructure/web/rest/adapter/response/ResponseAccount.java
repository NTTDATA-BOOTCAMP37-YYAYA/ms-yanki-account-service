package com.nttdata.bootcamp.msyankiaccount.infrastructure.web.rest.adapter.response;

import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAccount {
  
  private int httpStatus;
  private Account account;
  private String message;

}