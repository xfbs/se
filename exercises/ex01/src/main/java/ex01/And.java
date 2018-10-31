package ex01;

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
}
