package com.example.ideation.assemble;


import com.example.ideation.assemble.ProductResponse.AuthorDetail;
import com.example.ideation.assemble.ProductResponse.Authors;
import com.example.ideation.assemble.func.RespProdMapper;
import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import com.example.ideation.assemble.type.AuthorType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
public class DefaultService {

  //  @Qualifier("productMemoryRepository")
  private final Map<Product, List<ProductAuthor>> repo;

  public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) {
    Long targetId = Long.parseLong(id);
    var prod = repo.keySet().stream().filter(p -> p.getId().equals(targetId)).findFirst()
        .orElseThrow(RuntimeException::new);

    return ResponseEntity.ok().body(ProductResponse.transform(transformer(prod, repo.get(prod))));
  }


  //region private below
  private RespProdMapper transformer(Product product, List<ProductAuthor> authors) {
    return () -> {
      var m = authors.stream().collect(
          Collectors.groupingBy(ProductAuthor::getAuthorType, Collectors.toList()));
      return new ProductResponse(product.getTitle(), product.getStatus(), product.getGradeOfAge(),
          map(m));
    };
  }

  private List<Authors> map(Map<AuthorType, List<ProductAuthor>> m) {
    return m.entrySet().stream().map(
        e -> new Authors(e.getKey(),
            e.getValue().stream().map(this::map).toList())).toList();
  }

  private AuthorDetail map(ProductAuthor author) {
    return new AuthorDetail(author.getAuthorId(), author.getName(),
        author.getNickname());
  }

  //endregion

}
