import static org.junit.Assert.*;
import org.junit.Test;
import ex01.BooleanExpression;
import ex01.Var;

public class TestBooleanExpression {
  @Test
  public void can_parse_val() {
    BooleanExpression expr = BooleanExpression.parseExpression("a");
    assertTrue(expr instanceof Var);
    Var a = (Var) expr;
    assertEquals(a.getName(), "a");
  }
}
