package com.example.ideation.assemble;

import java.util.function.Function;

public interface Assembler<I, O> {
  O assemble(I input);

}
