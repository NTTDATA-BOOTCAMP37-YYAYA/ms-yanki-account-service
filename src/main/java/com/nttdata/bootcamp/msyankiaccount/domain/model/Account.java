package com.nttdata.bootcamp.msyankiaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  
  private String accountId;
  private String customerDocument;
  private String customerDocumentTypeId;
  private String accountCelImei;
  private String accountEmail;
  private String accountState;

}
