package ar.com.kfgodel.pairs;

import java.util.Objects;

/**
 * This type represents a structured tuple of 2 elements
 * Date: 18/11/19 - 18:59
 */
public class Pair<F,S> {

  private F first;
  private S second;

  public F getFirst() {
    return first;
  }

  public void setFirst(F first) {
    this.first = first;
  }

  public S getSecond() {
    return second;
  }

  public void setSecond(S second) {
    this.second = second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return first.equals(pair.first) &&
      second.equals(pair.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "{" + first + ", " + second + "}";
  }

  public static<F,S> Pair<F,S> create(F first, S second) {
    Pair<F,S> pair = new Pair<>();
    pair.first = first;
    pair.second = second;
    return pair;
  }

}
