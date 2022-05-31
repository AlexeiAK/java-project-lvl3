package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> appliedMethods = new ArrayList<>();

    public final List<Predicate> getAppliedMethods() {
        return appliedMethods;
    }

    public final boolean isValid(Object testedObject) {
        Predicate<Object> finalPredicate = null;

        boolean result = true;

        if (appliedMethods.size() == 0) {
            finalPredicate = o -> o == null || o.equals("");
        } else {
            for (Predicate predicate : appliedMethods) {
                finalPredicate = predicate;
                boolean resultOfPredicate = finalPredicate.test(testedObject);

                if (!resultOfPredicate) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}
