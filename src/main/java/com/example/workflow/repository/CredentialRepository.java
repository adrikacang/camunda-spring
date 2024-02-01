package com.example.workflow.repository;

import com.example.workflow.model.Credential;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CredentialRepository extends CrudRepository<Credential, Long> {
    List<Credential> findByName(String name);

    Credential findById(long id);


}
