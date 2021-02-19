package com.mycompany.noteappdat.model.entity;

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
public class FolderInFolder implements Serializable {
	@Id 
        private Folder parent;
	private Folder child;
}
