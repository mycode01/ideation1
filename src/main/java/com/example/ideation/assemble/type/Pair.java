package com.example.ideation.assemble.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair<T1, T2> {
  private final T1 one;

  private final T2 two;
}
