package com.whiteclarkegroup.liquibaselinter.linters;

import com.whiteclarkegroup.liquibaselinter.Linter;
import com.whiteclarkegroup.liquibaselinter.config.rules.RuleRunner;
import com.whiteclarkegroup.liquibaselinter.config.rules.RuleType;
import liquibase.change.core.AddPrimaryKeyChange;
import liquibase.exception.ChangeLogParseException;

public class AddPrimaryKeyChangeLinter implements Linter<AddPrimaryKeyChange> {

    private static final ObjectNameLinter objectNameLinter = new ObjectNameLinter();

    @Override
    public void lint(AddPrimaryKeyChange change, RuleRunner ruleRunner) throws ChangeLogParseException {
        getObjectNameLinter().lintObjectNameLength(change.getConstraintName(), change, ruleRunner);
        ruleRunner.forChange(change)
                .run(RuleType.PRIMARY_KEY_NAME, change.getConstraintName());
    }

    ObjectNameLinter getObjectNameLinter() {
        return objectNameLinter;
    }

}
