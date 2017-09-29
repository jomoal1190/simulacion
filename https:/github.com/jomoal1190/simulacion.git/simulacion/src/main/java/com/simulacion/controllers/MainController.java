package com.simulacion.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	
    @GetMapping(value = "/hello")
    public String Hello(){

    return "Hola Majeee";

    	
    }
	
	
    
    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public Object viewHome() throws IOException {
    	
//    	try {
//  		restCountries.getCountries();
//  	} catch (Exception e) {
//  		// TODO Auto-generated catch block
//  		e.printStackTrace();
//  	}
//<>
        return null; 
    }

}
