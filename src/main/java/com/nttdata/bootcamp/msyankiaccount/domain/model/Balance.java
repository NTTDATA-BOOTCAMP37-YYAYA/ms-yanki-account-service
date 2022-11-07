package com.nttdata.bootcamp.msyankiaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
  
  private String balanceId;
  private String accountId;
  private double balanceAmmount;

}
