import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex01.Not;
import ex01.Var;

public class TestNot {
  final Var a = new Var("a");
  final Var b = new Var("b");

  @Test
  public void class_exists() {
    var subject = new Not(a);
  }

  @Test
  public void can_get_op() {
    var subject = new Not(a);
    assertEquals(subject.getOp(), a);
  }

  @Test
  public void can_export() {
    var subject = new Not(a);
    assertEquals(subject.toPostfixString(), "a !");
  }

  @Test
  public void can_evaluate() {
    Map<String, Boolean> params = new HashMap<>();
    params.put("a", true);
    params.put("b", false);
    var nota = new Not(a);
    var notb = new Not(b);
    assertEquals(nota.evaluate(params), false);
    assertEquals(notb.evaluate(params), true);
  }

  @Test
  public void can_make_disjunctive_terms() {
    var subject = new Not(a);
    var terms = subject.disjunctiveTerms();
    assertEquals(terms.size(), 1);
    assertEquals(terms.get(0), subject);
  }
}
