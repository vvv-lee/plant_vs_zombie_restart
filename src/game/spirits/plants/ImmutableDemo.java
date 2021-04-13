package game.spirits.plants;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableDemo {
  public static void main(String[] args) {
    List<String> originalList = new ArrayList<>();
    originalList.add("a");
    originalList.add("b");
    originalList.add("c");

    List<String> jdkUnmodifiableList = Collections.unmodifiableList(originalList);
    List<String> guavaImmutableList = ImmutableList.copyOf(originalList);

     guavaImmutableList.add("d"); // 抛出UnsupportedOperationException
    originalList.add("d");

    print(originalList); // a b c d
    print(jdkUnmodifiableList); // a b c d
    print(guavaImmutableList); // a b c
  }

  private static void print(List<String> list) {
    for (String s : list) {
      System.out.print(s + " ");
    }
    System.out.println();
  }
}