package ex01;

class Not implements BooleanExpression {
  final BooleanExpression op;

  public Not(BooleanExpression op) {
    this.op = op;
  }

  public BooleanExpression getOp() {
    return op;
  }
}
