package ex01;
import java.util.Map;

public class Not implements BooleanExpression {
  final BooleanExpression op;

  public Not(BooleanExpression op) {
    this.op = op;
  }

  public BooleanExpression getOp() {
    return op;
  }

  public String toPostfixString() {
    return String.join(" ", op.toPostfixString(), "!");
  }

  public boolean evaluate(Map<String, Boolean> params) {
    return !op.evaluate(params);
  }
}
