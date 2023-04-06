package com.example.ideation.assemble.near_repo;

import com.example.ideation.assemble.type.AgeGrade;
import com.example.ideation.assemble.type.AuthorId;
import com.example.ideation.assemble.type.ProductStatus;
import lombok.AllArgsConstructor;

import java.util.List;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final Long id;
    private final String title;
    private final ProductStatus status;
    private final AgeGrade gradeOfAge;
    private final List<AuthorId> authors;

}