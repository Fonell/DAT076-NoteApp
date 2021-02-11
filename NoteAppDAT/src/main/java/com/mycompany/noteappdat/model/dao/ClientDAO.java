package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class ClientDAO extends AbstractDAO<Client> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public ClientDAO() {
        super(Client.class);
    }

    public Client findClientMatchingCID(String cid) {
        return entityManager.find(Client.class, cid);
    }
}