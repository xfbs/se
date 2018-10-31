import org.junit.Assert;
import org.junit.Test;
import ex01.Not;
import ex01.Var;

public class TestNot {
  final Var a = new Var("a");

  @Test
  public void class_exists() {
    var subject = new Not(a);
  }

  @Test
  public void can_get_op() {
    var subject = new Not(a);
    Assert.assertEquals(subject.getOp(), a);
  }
}
