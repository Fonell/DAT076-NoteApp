package com.mycompany.noteappdat.model.entity;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {

    @Id
    @NonNull
    private String title;
    
    @NonNull
    private String text;
}
