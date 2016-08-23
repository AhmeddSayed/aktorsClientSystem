/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.controllers;

import com.kreative.aktorsclientsystem.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kreative.aktorsclientsystem.models.Client;
import java.util.Collection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ahmed
 */
@RestController
@RequestMapping("/clients")
@ComponentScan(basePackages = {"com.kreative.aktorsclientsystem.models"})
public class ClientsRestController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Client>> getAllClients() {
        return new ResponseEntity<>((Collection<Client>) clientRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Client> getClientWithId(@PathVariable Long id) {
        return new ResponseEntity<>(clientRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<Client> findClientWithName(@RequestParam(value = "id") long clientId) {
        return new ResponseEntity<>(clientRepository.findOne(clientId), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addClient(@RequestBody Client input) {
        return new ResponseEntity<>(clientRepository.save(input), HttpStatus.CREATED);
    }
}
