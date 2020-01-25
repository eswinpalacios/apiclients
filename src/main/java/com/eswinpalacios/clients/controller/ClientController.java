package com.eswinpalacios.clients.controller;

import com.eswinpalacios.clients.model.Client;
import com.eswinpalacios.clients.dto.KPIResponse;
import com.eswinpalacios.clients.service.IClientService;
import com.eswinpalacios.clients.utils.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    public List<Client> list(){
        return clientService.clients();
    }

    @PostMapping("/client")
    public ResponseEntity save(@Valid @RequestBody Client client, BindingResult result) {
        ClientValidation clientValidation = new ClientValidation();
        clientValidation.validate(client, result);

        if(result.hasErrors()){
            String[] err = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).toArray(String[]::new);
            HashMap errs = new HashMap();
            errs.put("errors", err);

            return new ResponseEntity(errs, HttpStatus.BAD_REQUEST);
        }

        clientService.save(client);

        return new ResponseEntity(client, HttpStatus.CREATED);

    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity list(@PathVariable("id") Integer id){
        return new ResponseEntity(clientService.get(id).get(), HttpStatus.OK);
    }

    @GetMapping("/kpi")
    public KPIResponse kpi(){
        return clientService.kpi();
    }
}
