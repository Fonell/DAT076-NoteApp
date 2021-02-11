package com.mycompany.noteappdat.model.entity;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(NotePK.class)
public class Note implements Serializable {
    @Id private Client owner;
    @Id private String title;
    //private NotePK inFolder;
    private String text;
}
