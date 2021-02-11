package com.mycompany.noteappdat.model.entity;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(NotePK.class)
public class Note implements Serializable {
    @Id private Client owner;
    @Id private String title;
    //private NotePK inFolder;
    private String text;

    public Note() {
    }

    public Note(Client owner, String title, String text) {
        this.owner = owner;
        this.title = title;
        this.text = text;
    }
}
/*
public class NotePK implements Serializable {
    private Client owner;
    private String title;
       
    public int hashCode() {
        return (int) owner.hashCode() + title.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof com.mycompany.noteappdat.model.dao.key.NotePK)) return false;
        if (obj == null) return false;
        NotePK pk = (NotePK) obj;
        return pk.owner == owner && pk.title.equals(title);
    }
}
*/