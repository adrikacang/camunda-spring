package com.example.workflow;

import com.example.workflow.model.Credential;
import com.example.workflow.repository.CredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.workflow.model"})
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo(CredentialRepository repository) {
    return (args -> {
      repository.save(new Credential("key-1", "ASDASDASD"));
      repository.save(new Credential("key-2", "BCDBCDBCD"));

      log.info("Find All");
      log.info("---------");
      repository.findAll().forEach(credential -> {
        log.info(credential.toString());
      });
      log.info("");

      log.info("Credentials:");
      log.info("------------");
      List<Credential> credentialList = repository.findByName("key-1");
      credentialList.forEach(credential -> {
        log.info(credential.toString());
      });
    });
  }
}