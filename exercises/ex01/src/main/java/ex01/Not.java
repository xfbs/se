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

  public BooleanExpression toDNF() {
    // DNF(!a) = !a
    if(op instanceof Var) {
      return this;
    }

    // DNF(!!A) = DNF(A)
    if(op instanceof Not) {
      return ((Not)op).getOp().toDNF();
    }

    // DNF(!(A&B)) = DNF(!A)|DNF(!B)
    if(op instanceof And) {
      And and = (And) op;
      return new Or(new Not(and.getLeftOp()), new Not(and.getRightOp())).toDNF();
    }

    throw new IllegalArgumentException("unreconised argument");
  }
}
