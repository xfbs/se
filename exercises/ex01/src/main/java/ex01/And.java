package ex01;
import java.util.Map;
import java.util.Stack;

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

  public BooleanExpression toDNF() {
    var ldnf = lhs.toDNF();
    var rdnf = rhs.toDNF();

    // if none of the arguments are Or, then there's nothing we need to do here.
    if(!(ldnf instanceof Or) && !(rdnf instanceof Or)) {
      return new And(ldnf, rdnf);
    }

    // if one (or more) of the arguments are Or, then we need to handle that.
    var terms = new Stack<And>();
    for(var left_term : ldnf.disjunctiveTerms()) {
      for(var right_term : rdnf.disjunctiveTerms()) {
        // take all pairs of disjunctive terms and combine them.
        terms.add(new And(left_term, right_term));
      }
    }

    // use the list of terms and OR them all together. this could be done in a more
    // balanced fashion, but I was too lazy.
    var dnf = new Or(terms.pop(), terms.pop());
    while(!terms.empty()) {
      dnf = new Or(dnf, terms.pop());
    }

    return dnf;
  }
}
