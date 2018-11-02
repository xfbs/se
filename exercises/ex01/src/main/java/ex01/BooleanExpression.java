package ex01;
import java.util.Stack;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public interface BooleanExpression {
  // parse an reverse-polish boolean expression, so that for example "a a &" gets turned
  // into And(Var("a"), Var("a")).
  public static BooleanExpression parseExpression(String expr) {
    // stack to hold arguments. notice how long that is? yeah, I'm not a huge fan of java.
    Stack<BooleanExpression> stack = new Stack<>();

    // split expression at whitespace, loop over items.
    for(String current : expr.split("\\s+")) {
      // ignore any token that has zero length.
      if(current.length() == 0) {
        continue;
      }

      // bette to ask for forgiveness than to ask for permission. we'll access the stack
      // here, and wrap the whole thing in a try/catch block to guard against situations
      // where people use an op and there aren't enough arguments on the stack. this would
      // have been *much* shorter in ruby. 
      try {
        if(current.length() == 1) switch(current.charAt(0)) {
          case '&': stack.push(new And(stack.pop(), stack.pop())); continue;
          case '|': stack.push(new Or(stack.pop(), stack.pop())); continue;
          case '!': stack.push(new Not(stack.pop())); continue;
        }

        // if it ain't empty and it ain't an operator, it's gotta be a var.
        stack.push(new Var(current));
      } catch(java.util.EmptyStackException e) {
        throw new IllegalArgumentException("can't access parameters on the stack.");
      } catch(Exception e) {
        // if it's an exception we don't recognise, we don't give a fuck and just pass
        // it down.
        throw e;
      }
    }

    // make sure there are no arguments remaining on the stack. this happens when someone
    // passes too many, for example in "a a !", where ! is a unary operator.
    if(stack.size() != 1) {
      throw new IllegalArgumentException("expression can't be empty or have extra aguments");
    }

    return stack.pop();
  }

  // convert a parsed boolean expression back to reverse-polish notation.
  public String toPostfixString();

  // evaluate a boolean expression, given a list of truthiness values of the inputs.
  public boolean evaluate(Map<String, Boolean> param);

  // given a boolean expression, generates a list of disjunctive terms that expresses the
  // boolean expression.
  default public List<BooleanExpression> disjunctiveTerms() {
    var terms = new ArrayList<BooleanExpression>();
    terms.add(this);
    return terms;
  }

  // converts a boolean expression into the disjunctive normal form.
  public BooleanExpression toDNF();
}
