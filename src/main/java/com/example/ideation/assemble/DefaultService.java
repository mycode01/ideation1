package com.example.ideation.assemble;


import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import java.util.List;
import java.util.Map;
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

    return ResponseEntity.ok().body(ProductResponse.from(prod, repo.get(prod)));
  }

}
