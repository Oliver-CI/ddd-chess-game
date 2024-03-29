package ddd.core.businessrules;


import java.util.ArrayList;
import java.util.List;

public class BusinessRuleViolationException extends RuntimeException {

    private final List<BusinessRuleViolation> violations;

    public BusinessRuleViolationException() {
        violations = new ArrayList<>();
    }

    public BusinessRuleViolationException(List<BusinessRuleViolation> violations) {
        super("Rule Violations: " + violations.size() + " violations have been detected.");
        this.violations = violations;
    }

    public List<BusinessRuleViolation> getViolations() {
        return violations;
    }
}
