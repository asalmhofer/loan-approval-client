package com.salmhofer.loanapprovalclient.core.controller;

import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salmhofer.loanapprovalclient.core.bean.CustomizedResponse;
import com.salmhofer.loanapprovalclient.core.bean.LoanRequest;
import com.salmhofer.loanapprovalclient.core.dto.LoanRequestDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class MainController {
	
	private final static Logger logger = Logger.getLogger("MainController");

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }
    
    @RequestMapping(value = "/request-loan-be", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
    public CustomizedResponse requestLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        System.out.println("Received request (backend controller): " + loanRequestDTO);
        
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setAge(loanRequestDTO.getAge());
        loanRequest.setAmount(loanRequestDTO.getAmount());
        loanRequest.setCustomerName(loanRequestDTO.getCustomerName());
        loanRequest.setChildren(loanRequestDTO.getChildren());
        loanRequest.setJob(loanRequestDTO.getJob());
        
        CustomizedResponse r = triggerLoanApprovalWorkflow(loanRequest);
    	return r;
    }
    
    private CustomizedResponse triggerLoanApprovalWorkflow(LoanRequest loanRequest) {
    	
    	//Create JSON object with form data to fill the Camunda variables
    	JSONObject data = new JSONObject();
		JSONObject var = new JSONObject();
		
		JSONObject nameData = new JSONObject();
		JSONObject amountData = new JSONObject();
		JSONObject ageData = new JSONObject();
		JSONObject childrenData = new JSONObject();
		JSONObject jobData = new JSONObject();
		
		try {
			nameData.put("value", loanRequest.getCustomerName());
			nameData.put("type", "String");
			
			amountData.put("value", loanRequest.getAmount().toString());
			amountData.put("type", "Double");
			
			ageData.put("value", loanRequest.getAge().toString());
			ageData.put("type", "Integer");
			
			childrenData.put("value", loanRequest.getChildren().toString());
			childrenData.put("type", "Integer");
			
			jobData.put("value", loanRequest.getJob());
			jobData.put("type", "String");
			
			data.put("customerName", nameData);
			data.put("customerAmount", amountData);
			data.put("customerAge", ageData);
			data.put("customerJob", jobData);
			data.put("customerChildren", childrenData);
			
			var.put("variables", data);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		logger.info("JSON loan request variables: " + var);
		
		//Start the process via triggering a REST POST request
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/engine-rest/process-definition/key/approve-loan/start");

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, var.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		logger.info("Process 'approve-loan' successfully triggered with status code " + response.getStatus());
		
		CustomizedResponse r = new CustomizedResponse();
		r.setMessage("Loan request process successfully started!");
		return r; 
    }
}
