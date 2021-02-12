package com.mycompany.noteappdat.model.dao.key;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NotePK implements Serializable {
    @Id
    private String owner;
    @Id
    private String title;
}