import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex02.*;

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
    assertEquals(subject.toPostfixString(), "a b |");
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

  @Test
  public void can_make_disjunctive_terms() {
    var subject = new Or(a, b);
    var terms = subject.disjunctiveTerms();
    assertEquals(terms.size(), 2);
    assertEquals(terms.get(0), a);
    assertEquals(terms.get(1), b);
  }

  @Test
  public void can_make_nested_disjunctive_terms() {
    var subject = new Or(new Or(a, b), new Or(a, b));
    var terms = subject.disjunctiveTerms();
    assertEquals(terms.size(), 4);
    assertEquals(terms.get(0), a);
    assertEquals(terms.get(1), b);
    assertEquals(terms.get(2), a);
    assertEquals(terms.get(3), b);
  }

  @Test
  public void can_generate_dnf_with_or() {
    // DNF(!!a|!b) == a|!b
    var subject = new Or(new Not(new Not(a)), new Not(b));
    var dnf = subject.toDNF();
    assertTrue(dnf instanceof Or);
    var left = ((Or)dnf).getLeftOp();
    var notright = ((Or)dnf).getRightOp();
    assertTrue(left instanceof Var);
    assertEquals(left, a);
    assertTrue(notright instanceof Not);
    var right = ((Not)notright).getOp();
    assertTrue(right instanceof Var);
    assertEquals(right, b);
  }
}
