// Sirve para validar los sponsorship segun las restricciones dadas en el enunciado, que son las siguientes:
// - Sponsorships cannot be published unless they have at least one donation
// - Start moment - end moment must be a valid time interval in future wrt. the moment when a sponsorship is published
// Notar que solo deben de aplicar si draftmode == false. No importa si no se valida cuando draftmode == true

package acme.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SponsorshipValidator.class)
@ReportAsSingleViolation

public @interface ValidSponsorship {

	// Standard validation properties -----------------------------------------

	String message() default "";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
