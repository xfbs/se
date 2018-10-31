package ex01;
import java.util.Map;

public class And implements BooleanExpression {
  final BooleanExpression lhs;
  final BooleanExpression rhs;

  public And(BooleanExpression lhs, BooleanExpression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public BooleanExpression getLeftOp() {
    return this.lhs;
  }

  public BooleanExpression getRightOp() {
    return this.rhs;
  }

  public String toPostfixString() {
    return String.join(" ", rhs.toPostfixString(), lhs.toPostfixString(), "&");
  }

  public boolean evaluate(Map<String, Boolean> params) {
    return rhs.evaluate(params) && lhs.evaluate(params);
  }
}
