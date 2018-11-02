package ex01;
import java.util.Map;

public class Var implements BooleanExpression {
  final String name;

  public Var(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String toPostfixString() {
    return name;
  }

  public boolean evaluate(Map<String, Boolean> params) {
    if(!params.containsKey(name)) {
      throw new IllegalArgumentException("value must be in parameter list.");
    }

    return params.get(name);
  }

  public BooleanExpression toDNF() {
    return this;
  }
}
