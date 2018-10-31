package ex01;
import java.util.Map;

public class Or implements BooleanExpression {
  final BooleanExpression lhs;
  final BooleanExpression rhs;

  public Or(BooleanExpression lhs, BooleanExpression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public BooleanExpression getLeftOp() {
    return lhs;
  }

  public BooleanExpression getRightOp() {
    return rhs;
  }

  public String toPostfixString() {
    return String.join(" ", rhs.toPostfixString(), lhs.toPostfixString(), "|");
  }

  public boolean evaluate(Map<String, Boolean> param) {
    return rhs.evaluate(param) || lhs.evaluate(param);
  }
}
