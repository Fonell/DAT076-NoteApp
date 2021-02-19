package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    
    @ManyToOne
    @JoinColumn(name = "fk_folder")
    private Folder folder;
}
