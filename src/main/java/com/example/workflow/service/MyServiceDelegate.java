package com.example.workflow.service;

import com.example.workflow.model.Credential;
import com.example.workflow.repository.CredentialRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myServiceDelegate")
public class MyServiceDelegate implements JavaDelegate {

    private final CredentialRepository credentialRepository;

    @Autowired
    public MyServiceDelegate(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Get input data from the process execution
        String inputData = (String) execution.getVariable("userTaskData");
        // Simulate some processing
        List<Credential> result = credentialRepository.findByName(inputData);
        String outputData = result.isEmpty() ? "EMPTY" : result.get(0).getValue();

        // Set output data back to the process execution
        execution.setVariable("outputData", outputData);

        if(outputData.equals("EMPTY")) {
            throw new BpmnError("errorCode", "Data with name " + inputData +
                    " not found!. Please update the database");

        }
    }
}