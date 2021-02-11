package com.mycompany.noteappdat.model.dao.key;

import com.mycompany.noteappdat.model.entity.Client;
import java.io.Serializable;

public class NotePK implements Serializable {
    private Client owner;
    private String title;
    
    public NotePK() {}

    public NotePK(Client owner, String title) {
        this.owner = owner;
        this.title = title;
    }
       
    @Override
    public int hashCode() {
        return (int) owner.hashCode() + title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof NotePK)) return false;
        if (obj == null) return false;
        NotePK pk = (NotePK) obj;
        return pk.owner == owner && pk.title.equals(title);
    }
}