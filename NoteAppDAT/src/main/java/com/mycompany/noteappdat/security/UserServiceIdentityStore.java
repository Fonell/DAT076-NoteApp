package com.mycompany.noteappdat.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import lombok.AllArgsConstructor;
import lombok.Data;

@RequestScoped
public class UserServiceIdentityStore implements IdentityStore {
	@Data
	@AllArgsConstructor
	public static class UserRecord {
		private String email;
		private String password;
		private Set<String> roles;
	}

	private final UserRecord users[] = {
		new UserRecord("adam@adam.se", "testing123", new HashSet(Arrays.asList("admin", "user"))),
		new UserRecord("carl@carl.se", "testing456", new HashSet(Arrays.asList("user"))),
		new UserRecord("ulla@ulla.se", "testing789", new HashSet(Arrays.asList("user")))
	};

	public CredentialValidationResult validate(UsernamePasswordCredential credential) {
		for (UserRecord u : users) {
			if (credential.getCaller().equalsIgnoreCase(u.email) && 
			    credential.getPasswordAsString().equals(u.password))
				return new CredentialValidationResult(u.email, u.roles);
		}

		return CredentialValidationResult.NOT_VALIDATED_RESULT;
	}
}
