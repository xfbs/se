import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex01.*;

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

  @Test
  public void can_generate_dnf_with_var() {
    // test DNF(!a) == !a
    var subject = new Not(a);
    assertEquals(subject.toDNF(), subject);
  }

  @Test
  public void can_generate_dnf_with_not() {
    // test DNF(!!a) == a
    var subject = new Not(new Not(b));
    assertEquals(subject.toDNF(), b);
  }

  @Test
  public void can_generate_dnf_with_and() {
    // test DNF(!(a&b)) == (!a)|(!b)
    var subject = new Not(new And(a, b));
    assertTrue(subject.toDNF() instanceof Or);
    Or dnf = (Or) subject.toDNF();
    assertTrue(dnf.getLeftOp() instanceof Not);
    assertTrue(dnf.getRightOp() instanceof Not);
    assertEquals(((Not)dnf.getLeftOp()).getOp(), a);
    assertEquals(((Not)dnf.getRightOp()).getOp(), b);
  }

  @Test
  public void can_generate_dnf_with_and_nested_not() {
    // test DNF(!((!a)&(!b))) == a|b
    var a = new Not(this.a);
    var b = new Not(this.b);
    var subject = new Not(new And(a, b));
    assertTrue(subject.toDNF() instanceof Or);
    Or dnf = (Or) subject.toDNF();
    BooleanExpression left = dnf.getLeftOp();
    BooleanExpression right = dnf.getRightOp();
    assertTrue(left instanceof Var);
    assertTrue(right instanceof Var);
    assertEquals(left, this.a);
    assertEquals(right, this.b);
  }

  @Test
  public void can_generate_dnf_with_or() {
    // DNF(!(A|B)) = DNF(!A&!B)
    var subject = new Not(new Or(a, b));
    var dnf = subject.toDNF();
    assertTrue(dnf instanceof And);
    var left = ((And)dnf).getLeftOp();
    var right = ((And)dnf).getRightOp();
    assertTrue(left instanceof Not);
    assertTrue(right instanceof Not);
    assertEquals(((Not)left).getOp(), a);
    assertEquals(((Not)right).getOp(), b);
  }
}
