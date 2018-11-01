import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex01.And;
import ex01.Var;

public class TestAnd {
  final Var a = new Var("a");
  final Var b = new Var("b");

  @Test
  public void class_exists() {
    var subject = new And(a, b);
  }

  @Test
  public void can_get_left_op() {
    var subject = new And(a, b);
    assertEquals(subject.getLeftOp(), a);
  }

  @Test
  public void can_get_right_op() {
    var subject = new And(a, b);
    assertEquals(subject.getRightOp(), b);
  }

  @Test
  public void can_export() {
    var subject = new And(a, b);
    assertEquals(subject.toPostfixString(), "b a &");
  }

  @Test
  public void can_evaluate() {
    Map<String, Boolean> params = new HashMap<>();
    params.put("a", true);
    params.put("b", false);
    var aa = new And(a, a);
    var ab = new And(a, b);
    var ba = new And(b, a);
    var bb = new And(b, b);
    assertEquals(aa.evaluate(params), true);
    assertEquals(ab.evaluate(params), false);
    assertEquals(ba.evaluate(params), false);
    assertEquals(bb.evaluate(params), false);
  }

  @Test
  public void can_make_disjunctive_terms() {
    var subject = new And(a, b);
    var terms = subject.disjunctiveTerms();
    assertEquals(terms.size(), 1);
    assertEquals(terms.get(0), subject);
  }
}
