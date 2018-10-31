import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import ex01.Var;

public class TestVar {
  @Test
  public void class_exists() {
    var subject = new Var("a");
  }

  @Test
  public void can_get_name() {
    var subject = new Var("a");
    assertEquals(subject.getName(), "a");
  }

  @Test
  public void can_export() {
    var subject = new Var("abc");
    assertEquals(subject.toPostfixString(), "abc");
  }

  @Test
  public void can_evaluate() {
    Map<String, Boolean> params = new HashMap<>();
    params.put("x", true);
    params.put("y", false);
    var x = new Var("x");
    assertEquals(x.evaluate(params), params.get("x"));

    var y = new Var("y");
    assertEquals(y.evaluate(params), params.get("y"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void missing_param_raises_illegal_argument() {
    Map<String, Boolean> params = new HashMap<>();
    var x = new Var("x");
    x.evaluate(params);
  }
}
