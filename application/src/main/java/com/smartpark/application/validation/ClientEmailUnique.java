package com.smartpark.application.validation;

import com.smartpark.application.service.implmnts.client.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.UUID;

import static java.lang.annotation.ElementType.*;


/**
 * Validate that the email value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ClientEmailUnique.ClientEmailUniqueValidator.class
)
public @interface ClientEmailUnique {

    String message() default "{Exists.client.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ClientEmailUniqueValidator implements ConstraintValidator<ClientEmailUnique, String> {

        private final ClientService clientService;
        private final HttpServletRequest request;

        public ClientEmailUniqueValidator(final ClientService clientService,
                final HttpServletRequest request) {
            this.clientService = clientService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables = 
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(clientService.get(UUID.fromString(currentId)).getEmail())) {
                // value hasn't changed
                return true;
            }
            return clientService.emailDoesntExist(value);
        }

    }

}
