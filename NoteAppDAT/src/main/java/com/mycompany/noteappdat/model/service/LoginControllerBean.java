package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.user.UserBean;
import fish.payara.cdi.auth.roles.RolesPermitted;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
@DeclareRoles({"admin", "user"})
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(useForwardToLogin = false, loginPage = "/welcome.xhtml"))
public class LoginControllerBean {
	@Inject private UserBean userBean;
	@Inject private SecurityContext securityContext;

	//@RolesPermitted({"user", "admin"})
	public void onLogin() {
		final UsernamePasswordCredential credentials = new UsernamePasswordCredential(userBean.getEmail(),
			userBean.getPassword()
		);

		final AuthenticationParameters parameters = AuthenticationParameters.withParams().credential(credentials);

		final AuthenticationStatus status = securityContext.authenticate(Faces.getRequest(), 
			Faces.getResponse(), parameters
		);

		if (status != AuthenticationStatus.SUCCESS) {
			Messages.addGlobalError("Authentication failed!");
		}
                if (status == AuthenticationStatus.SUCCESS ){
                    Faces.redirect("index.xhtml");
                }
                
	}

	@RolesPermitted({"user", "admin"})
	public void onLogout() {
		Faces.invalidateSession();
		Faces.redirect(Faces.getRequest().getContextPath());
	}
}
