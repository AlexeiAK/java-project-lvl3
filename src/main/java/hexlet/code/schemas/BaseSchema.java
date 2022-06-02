package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> appliedMethods = new ArrayList<>();

    public final boolean isValid(Object testedObject) {
        Predicate<Object> finalPredicate = null;

        boolean result = true;

        if (appliedMethods.size() == 0) {
            finalPredicate = o -> o == null || o.equals("");
        } else {
            for (Predicate predicate : appliedMethods) {
                finalPredicate = predicate;
                boolean resultOfPredicate = finalPredicate.test(testedObject);

                // If one of result is false, then the others will be false
                if (!resultOfPredicate) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    protected final void addPredicate(Predicate predicate) {
        appliedMethods.add(predicate);
    }
}
