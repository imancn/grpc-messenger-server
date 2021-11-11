package ir.imancn.messenger.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

   @Value("${server.port}")
   String restPort;

   @Value("${grpc.server.port}")
   String grpcPort;

   @Bean
   public void portLog(){
      System.out.println("rest port  " + restPort);
      System.out.println("grpc port  " + grpcPort);
   }

}