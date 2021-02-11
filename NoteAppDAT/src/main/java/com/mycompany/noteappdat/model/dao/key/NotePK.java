package com.mycompany.noteappdat.model.dao.key;

import com.mycompany.noteappdat.model.entity.Client;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotePK implements Serializable {
    private Client owner;
    private String title;  
}