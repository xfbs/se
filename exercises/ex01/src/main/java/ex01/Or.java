package ex01;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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

  public List<BooleanExpression> disjunctiveTerms() {
    var terms = new ArrayList<BooleanExpression>();
    
    // recursively apply disjunctive terms, and add the results to the list of
    // terms.
    for(var term : lhs.disjunctiveTerms()) {
      terms.add(term);
    }

    for(var term : rhs.disjunctiveTerms()) {
      terms.add(term);
    }

    return terms;
  }

  public BooleanExpression toDNF() {
    return new Or(lhs.toDNF(), rhs.toDNF());
  }
}
