package ex01;

public class Var implements BooleanExpression {
  final String name;

  public Var(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
