package ex02;

import java.util.Map;

public class Var implements BooleanExpression {
    private final String name;

    public Var(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toPostfixString() {
        return name;
    }

    @Override
    public String toPrefixString() {
        return name;
    }

    @Override
    public boolean evaluate(Map<String, Boolean> argumentMap) {
        if(!argumentMap.containsKey(name)) {
          throw new IllegalArgumentException("value must be in parameter list.");
        }

        return argumentMap.get(name);
    }

    @Override
    public BooleanExpression toDNF() {
        return this;
    }

    @Override
    public boolean isVar() {
        return true;
    }
}
