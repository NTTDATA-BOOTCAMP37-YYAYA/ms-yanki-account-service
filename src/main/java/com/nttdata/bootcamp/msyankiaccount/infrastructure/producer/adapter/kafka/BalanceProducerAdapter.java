package com.nttdata.bootcamp.msyankiaccount.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msyankiaccount.application.outgoing.SendBalancePort;
import com.nttdata.bootcamp.msyankiaccount.domain.model.Balance;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class BalanceProducerAdapter implements SendBalancePort {
  
  final  Logger logger = LoggerFactory.getLogger(BalanceProducerAdapter.class);
  
  @Value("${kafka.topic.balance.create.name}")
  private String topic;

  private  final KafkaTemplate<String, Balance> kafkaTemplate;
  
  @Override
  public void sendBalance(Balance balance) {
    
    ListenableFuture<SendResult<String, Balance>> future = kafkaTemplate.send(this.topic, balance);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, Balance>>() {

      @Override
      public void onSuccess(SendResult<String, Balance> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the account account");
        
      }

    });
  }

}
