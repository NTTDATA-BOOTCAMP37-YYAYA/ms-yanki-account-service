package com.nttdata.bootcamp.msyankiaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**.*/
@SpringBootApplication
@EnableEurekaClient
public class MsYankiAccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsYankiAccountApplication.class, args);
  }

}
