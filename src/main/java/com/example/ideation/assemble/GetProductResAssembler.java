package com.example.ideation.assemble;

import com.example.ideation.assemble.ProductResponse.AuthorDetail;
import com.example.ideation.assemble.ProductResponse.Authors;
import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import com.example.ideation.assemble.type.AuthorType;
import com.example.ideation.assemble.type.Pair;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GetProductResAssembler implements Assembler<Pair<Product, List<ProductAuthor>>, ProductResponse> {

  @Override
  public ProductResponse assemble(Pair<Product, List<ProductAuthor>> input) {
    final var p = input.getOne();
    final var aut = input.getTwo();
    var m = aut.stream().collect(
        Collectors.groupingBy(ProductAuthor::getAuthorType, Collectors.toList()));
    return new ProductResponse(p.getTitle(), p.getStatus(), p.getGradeOfAge(),
        map(m));
  }

  private List<Authors> map(Map<AuthorType, List<ProductAuthor>> m) {
    return m.entrySet().stream().map(
        e -> new Authors(e.getKey(),
            e.getValue().stream().map(this::map).toList())).toList();
  }

  private AuthorDetail map(ProductAuthor author) {
    return new AuthorDetail(author.getAuthorId(), author.getName(), author.getNickname());
  }
}
