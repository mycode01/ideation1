package com.example.ideation.assemble;

import com.example.ideation.assemble.type.AuthorId;
import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import com.example.ideation.assemble.type.AgeGrade;
import com.example.ideation.assemble.type.AuthorType;
import com.example.ideation.assemble.type.ProductStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ProductResponse(
    String title,
    ProductStatus status,
    AgeGrade gradeOfAge,
    List<Authors> authors
) {

  public static ProductResponse from(Product product, List<ProductAuthor> authors) {
    var m = authors.stream().collect(
        Collectors.groupingBy(ProductAuthor::getAuthorType, Collectors.toList()));
    return new ProductResponse(product.getTitle(), product.getStatus(), product.getGradeOfAge(),
        Authors.getAuthors(m));
  }

  public record Authors(
      AuthorType type,
      List<AuthorDetail> details
  ) {

    public static List<Authors> getAuthors(Map<AuthorType, List<ProductAuthor>> m) {
      return m.entrySet().stream().map(
          e -> new Authors(e.getKey(),
              e.getValue().stream().map(AuthorDetail::from).toList())).toList();
    }
  }

  public record AuthorDetail(
      AuthorId id,
      String name,
      String nickname
  ) {
    public static AuthorDetail from(ProductAuthor author) {
      return new AuthorDetail(author.getAuthorId(), author.getName(), author.getNickname());
    }
  }
}
