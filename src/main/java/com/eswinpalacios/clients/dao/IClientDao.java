package com.eswinpalacios.clients.dao;

import com.eswinpalacios.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDao extends JpaRepository<Client, Integer> {

}
