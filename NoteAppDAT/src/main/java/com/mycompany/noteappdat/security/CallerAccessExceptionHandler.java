/* DELTASPIKE DEPENDENCIES CAUSE WEIRD ERROR
package com.mycompany.noteappdat.security;

import fish.payara.cdi.auth.roles.CallerAccessException;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.omnifaces.util.Messages;

@ExceptionHandler
public class CallerAccessExceptionHandler {
	public void report(@Handles ExceptionEvent<CallerAccessException> event) {
		Messages.addGlobalError(event.getException().getMessage());
		event.handled();
	}
}
*/