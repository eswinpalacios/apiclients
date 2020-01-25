package com.eswinpalacios.clients.service;

import com.eswinpalacios.clients.model.Client;
import com.eswinpalacios.clients.dto.KPIResponse;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client save(Client client);
    Optional<Client> get(int id);
    List<Client> clients();
    KPIResponse kpi();
}
