package ddd.game.rules;

import ddd.core.businessrules.BusinessRule;
import ddd.core.businessrules.BusinessRuleViolation;

import java.util.Collections;
import java.util.List;

public class PlayerIsActive extends BusinessRule {

    @Override
    public List<BusinessRuleViolation> checkRule() {

        return Collections.emptyList();
    }
}
