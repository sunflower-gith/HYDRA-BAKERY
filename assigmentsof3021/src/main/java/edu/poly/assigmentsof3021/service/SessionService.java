package edu.poly.assigmentsof3021.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	HttpSession session;
	
	public <T> T getAttribute(String name) {
		return (T) session.getAttribute(name);
	}
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}
	public void setLong(String name, Long value) {
		session.setAttribute(name, value);
	}
	public void setDouble(String name, Double value) {
		session.setAttribute(name, value);
	}
	public void remove(String name) {
		session.removeAttribute(name);
	}
}
