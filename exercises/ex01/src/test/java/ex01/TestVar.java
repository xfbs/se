import static org.junit.Assert.*;
import org.junit.Test;
import ex01.Var;

public class TestVar {
  @Test
  public void class_exists() {
    var subject = new Var("a");
  }

  @Test
  public void can_get_name() {
    var subject = new Var("a");
    Assert.assertEquals(subject.getName(), "a");
  }
}
