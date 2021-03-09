package com.mycompany.noteappdat.model.user;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Named
@SessionScoped
public class UserBean implements Serializable {
	@Email @NotBlank private String email;
	@NotBlank private String password;

	@Inject
	private SecurityContext securityContext;

	public boolean isLoggedIn() {
		return securityContext.getCallerPrincipal() != null;
	}
}
