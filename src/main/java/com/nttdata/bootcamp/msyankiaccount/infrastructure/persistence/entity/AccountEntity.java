package com.nttdata.bootcamp.msyankiaccount.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msyankiaccount.domain.enums.States;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Account")
public class AccountEntity {

  @Id
  private String accountId;
  private String customerDocument;
  private String customerDocumentTypeId;
  private String accountCelImei;
  private String accountEmail;
  private String accountState;


  /**.*/
  public static Account toAccount(AccountEntity accountEntity) {
    Account account = new Account();
    BeanUtils.copyProperties(accountEntity, account);
    return account;
  }
  
  /**.*/
  public static AccountEntity toAccountEntity(Account account) {

    AccountEntity accountEntity = new AccountEntity();
    BeanUtils.copyProperties(account, accountEntity);
    accountEntity.setAccountState(States.ACTIVE.getValue());
    return accountEntity;
  }
}
