import org.junit.Assert;
import org.junit.Test;
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
    Assert.assertEquals(subject.getLeftOp(), a);
  }

  @Test
  public void can_get_right_op() {
    var subject = new Or(a, b);
    Assert.assertEquals(subject.getRightOp(), b);
  }
}
