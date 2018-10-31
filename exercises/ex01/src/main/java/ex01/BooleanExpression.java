package ex01;
import java.util.Stack;

public interface BooleanExpression {
  public static BooleanExpression parseExpression(String expr) {
    // stack to hold arguments.
    Stack<BooleanExpression> stack = new Stack();

    // split expression at whitespace, loop over items.
    for(String current : expr.split("\\s+")) {
      // ignore any token that has zero length.
      if(current.length() == 0) {
        continue;
      }

      switch(current.charAt(0)) {
        case '&': stack.push(new And(stack.pop(), stack.pop())); break;
        case '|': stack.push(new Or(stack.pop(), stack.pop())); break;
        case '!': stack.push(new Not(stack.pop())); break;
        default:  stack.push(new Var(current)); break;
      }
    }

    if(stack.size() != 1) {
      throw new IllegalArgumentException("expression can't be empty or have extra aguments");
    }

    return stack.pop();
  }
}
