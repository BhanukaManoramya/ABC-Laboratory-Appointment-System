package com.abclabs.business.restwebservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abclabs.business.entity.CustomerSupport;
import com.abclabs.business.entity.PaymentCard;
import com.abclabs.business.restwebclient.RESTWebClient;
import com.abclabs.business.utilities.MyEmailSender;

@RestController
@RequestMapping("/api")
public class EmailRESTWebServices {

	@Autowired
	RESTWebClient restWebClient;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@PostMapping("/emails")
	public String sendEmail(@RequestBody PaymentCard paymentCard){
		String message = "You payment was not made. Try again!";
		try {
			message = MyEmailSender.generateReceipt(paymentCard);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	@PostMapping("/emails/support")
	public String supportCustomer(@RequestBody CustomerSupport customerSupport){
		String message = "Message did not send. Try again!";
		try {
			message = MyEmailSender.supportCustomer(customerSupport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
}
