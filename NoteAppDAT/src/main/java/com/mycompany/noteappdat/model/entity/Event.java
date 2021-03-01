package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name="Event")
@NoArgsConstructor
@RequiredArgsConstructor
public class Event implements Serializable {
    
    @Id
    @NonNull
    private String name;
    
    @ManyToOne
    private Note note;
}
