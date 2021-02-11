package com.mycompany.noteappdat.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Client implements Serializable {

    @Exclude
    @OneToMany(mappedBy = "owner")
    private List<Note> notes;
    
    @Id
    @NonNull
    private String cid;
    
    @NonNull
    private String name;
}
