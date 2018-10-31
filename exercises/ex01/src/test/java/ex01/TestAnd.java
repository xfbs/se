import static org.junit.Assert.*;
import org.junit.Test;
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
}
