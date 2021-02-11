package com.mycompany.noteappdat.model.entity;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {
        @Id private NotePK pk;
	//private NotePK inFolder;
        private String title;
	private String text;

    public Note(Client owner, String title, String text) {
        this.pk = new NotePK(owner, title);
        this.title = title;
        this.text = text;
    }
}
