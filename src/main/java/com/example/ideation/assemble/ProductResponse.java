package com.example.ideation.assemble;

import com.example.ideation.assemble.func.RespProdMapper;
import com.example.ideation.assemble.type.AuthorId;
import com.example.ideation.assemble.type.AgeGrade;
import com.example.ideation.assemble.type.AuthorType;
import com.example.ideation.assemble.type.ProductStatus;

import java.util.List;

public record ProductResponse(
    String title,
    ProductStatus status,
    AgeGrade gradeOfAge,
    List<Authors> authors
) {

  public static ProductResponse transform(RespProdMapper fn) {
    return fn.get();
    // readme 참고
  }

  public record Authors(
      AuthorType type,
      List<AuthorDetail> details
  ) {

  }

  public record AuthorDetail(
      AuthorId id,
      String name,
      String nickname
  ) {

  }
}
