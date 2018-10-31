import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex01.Or;
import ex01.Var;

public class TestOr {
  final Var a = new Var("a");
  final Var b = new Var("b");

  @Test
  public void class_exists() {
    var subject = new Or(a, b);
  }

  @Test
  public void can_get_left_op() {
    var subject = new Or(a, b);
    assertEquals(subject.getLeftOp(), a);
  }

  @Test
  public void can_get_right_op() {
    var subject = new Or(a, b);
    assertEquals(subject.getRightOp(), b);
  }

  @Test
  public void can_export() {
    var subject = new Or(a, b);
    assertEquals(subject.toPostfixString(), "b a |");
  }

  @Test
  public void can_evaluate() {
    Map<String, Boolean> params = new HashMap<>();
    params.put("a", true);
    params.put("b", false);
    var aa = new Or(a, a);
    var ab = new Or(a, b);
    var ba = new Or(b, a);
    var bb = new Or(b, b);
    assertEquals(aa.evaluate(params), true);
    assertEquals(ab.evaluate(params), true);
    assertEquals(ba.evaluate(params), true);
    assertEquals(bb.evaluate(params), false);
  }
}
