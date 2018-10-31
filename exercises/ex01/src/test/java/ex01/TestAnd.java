import org.junit.Assert;
import org.junit.Test;
import ex01.And;
import ex01.Var;

public class TestAnd {
  @Test
  public void classExists() {
    Var a = new Var("a");
    Var b = new Var("b");

    And and = new And(a, b);
  }
}
