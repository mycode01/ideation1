package com.example.ideation.assemble;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ideation.ApplicationConfig;
import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import com.example.ideation.assemble.type.AgeGrade;
import com.example.ideation.assemble.type.AuthorType;
import com.example.ideation.assemble.type.Pair;
import com.example.ideation.assemble.type.ProductStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultServiceTest {

  Map<Product, List<ProductAuthor>> repo;
  AuthorIdGenerator idGenerator;

  Assembler productResponseAssembler;

  ObjectMapper mapper;

  @BeforeEach
  void init(){
    mapper = new ObjectMapper();
    mapper.registerModule(new ApplicationConfig().simpleModule());
    idGenerator = new IdGenerator();
    repo = new HashMap<>();

    productResponseAssembler = new GetProductResAssembler();


    var a = List.of(new ProductAuthor(idGenerator.authorId(), "King sejong", "sejong the great",
        AuthorType.WRITER),
        new ProductAuthor(idGenerator.authorId(), "jeong inji", "Hakyeokjae", AuthorType.EDITOR));
    repo.put(new Product(1l, "Hunminjeongeum", ProductStatus.PUBLISHED, AgeGrade.G,
        a.stream().map(ProductAuthor::getAuthorId).toList()), a);
  }

  @Test
  public void getProduct() {
    var m = new HashMap<Product, List<ProductAuthor>>();

    var service = new DefaultService(repo, productResponseAssembler);
    var dto = service.getProduct("1").getBody();

    try {
      var res = mapper.writeValueAsString(dto);
      Assertions.assertTrue(res.contains("Hunminjeongeum"));
      Assertions.assertTrue(res.contains("King sejong"));
      Assertions.assertTrue(res.contains("jeong inji"));
      Assertions.assertTrue(res.toLowerCase().contains("published"));
      assertSame(dto.gradeOfAge(), AgeGrade.G);
      assertEquals(2, dto.authors().size());
      System.out.println(res);
    } catch (JsonProcessingException e) {
      Assertions.fail(e);
    }
  }

}