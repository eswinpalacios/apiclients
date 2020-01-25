package com.eswinpalacios.clients.service;

import com.eswinpalacios.clients.dao.IClientDao;
import com.eswinpalacios.clients.dto.AdditionalInfo;
import com.eswinpalacios.clients.model.Client;
import com.eswinpalacios.clients.dto.KPIResponse;
import com.eswinpalacios.clients.utils.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientImplement implements IClientService {

    @Autowired
    IClientDao iClientDao;

    @Override
    public Client save(Client client) {
        //ClientValidation2.validate(client.getDateOfBirth(), client.getAge());
        return this.iClientDao.save(client);
    }

    @Override
    public List<Client> clients() {
        List<Client> clients = iClientDao.findAll();

        for (Client client: clients) {
            AdditionalInfo additionalInfo = new AdditionalInfo();
            additionalInfo.setDateOfDie(ClientUtil.dateOfDie(client.getDateOfBirth()));
            client.setAdditionalInfo(additionalInfo);
        }

        return clients;
    }

    @Override
    public KPIResponse kpi() {
        KPIResponse kpi = new KPIResponse();
        List<Client> clients = iClientDao.findAll();

        kpi.setAverage(ClientUtil.ageAverage(clients));
        kpi.setStandarDeviation(ClientUtil.standardDeviation(clients));

        return kpi;
    }

    @Override
    public Optional<Client> get(int id) {
        return this.iClientDao.findById(id);
    }
}
