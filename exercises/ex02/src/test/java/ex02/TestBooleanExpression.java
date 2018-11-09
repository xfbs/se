import static org.junit.Assert.*;
import org.junit.Test;
import ex02.*;

public class TestBooleanExpression {
  @Test
  public void can_parse_val() {
    BooleanExpression expr = BooleanExpression.parseExpression("a");
    assertTrue(expr instanceof Var);
    Var a = (Var) expr;
    assertEquals(a.getName(), "a");
  }

  @Test
  public void can_parse_not() {
    BooleanExpression expr = BooleanExpression.parseExpression("a !");
    assertTrue(expr instanceof Not);
    Not not = (Not) expr;
    assertTrue(not.getOp() instanceof Var);
    Var a = (Var) not.getOp();
    assertEquals(a.getName(), "a");
  }

  @Test
  public void can_parse_and() {
    BooleanExpression expr = BooleanExpression.parseExpression("a b &");
    assertTrue(expr instanceof And);
    And and = (And) expr;
    
    assertTrue(and.getLeftOp() instanceof Var);
    Var a = (Var) and.getLeftOp();

    assertTrue(and.getRightOp() instanceof Var);
    Var b = (Var) and.getRightOp();
    
    assertEquals(a.getName(), "a");
    assertEquals(b.getName(), "b");
  }

  @Test
  public void can_parse_or() {
    BooleanExpression expr = BooleanExpression.parseExpression("a b |");
    assertTrue(expr instanceof Or);
    Or or = (Or) expr;
    
    assertTrue(or.getLeftOp() instanceof Var);
    Var a = (Var) or.getLeftOp();

    assertTrue(or.getRightOp() instanceof Var);
    Var b = (Var) or.getRightOp();
    
    assertEquals(a.getName(), "a");
    assertEquals(b.getName(), "b");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void raises_exception_on_unused_stack() {
    BooleanExpression.parseExpression("a a a &");
  }

  @Test(expected = IllegalArgumentException.class)
  public void raises_exception_on_illegal_stack() {
    BooleanExpression.parseExpression("!");
  }
}
