package com.example.ideation;

import com.example.ideation.assemble.type.AuthorId;
import com.example.ideation.assemble.IdGenerator;
import com.example.ideation.assemble.near_repo.Product;
import com.example.ideation.assemble.near_repo.ProductAuthor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public IdGenerator idGenerator() {
    return new IdGenerator();
  }

  @Bean("productMemoryRepository")
  public Map<Product, List<ProductAuthor>> productMemoryRepository() {
    return new HashMap<>();
  }

  @Bean
  public JsonSerializer<AuthorId> authorIdJsonSerializer() {
    return new JsonSerializer<AuthorId>() {
      @Override
      public void serialize(AuthorId value, JsonGenerator gen, SerializerProvider serializers)
          throws IOException {
        gen.writeString(value.toString());
      }
    };
  }

  @Bean
  public SimpleModule simpleModule() {
    SimpleModule module = new SimpleModule();
    module.addSerializer(AuthorId.class, authorIdJsonSerializer());
    return module;
  }
}
