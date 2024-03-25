package com.abclabs.business.restwebservice;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abclabs.business.entity.Appointment;
import com.abclabs.business.restwebclient.RESTWebClient;

@RestController
@RequestMapping("/api")
public class AppointmentRESTWebServices {

	@Autowired
	RESTWebClient restWebClient;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/appointments")
	public List<Appointment> getAppointments(){
		return restWebClient.getAppointments();
	}
	
	@GetMapping("/appointments/{appointmentID}")
	public Appointment getAppointment(@PathVariable int appointmentID) {
		return restWebClient.getAppointment(appointmentID);
	}
	
	@PostMapping("/appointments")
	public String saveAppointment(@RequestBody Appointment appointment) {
		return restWebClient.saveOrUpdateAppointment(appointment);
	}
	
	@PutMapping("/appointments")
	public Appointment updateAppointment(@RequestBody Appointment appointment) {
		restWebClient.saveOrUpdateAppointment(appointment);
		return appointment;
	}

	@DeleteMapping("/appointments/{appointmentID}")
	public void deleteAppointment(@PathVariable int appointmentID) {
		Appointment appointment = restWebClient.getAppointment(appointmentID);
		restWebClient.deleteAppointment(appointment);
	}
}
