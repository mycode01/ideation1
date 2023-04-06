package com.example.ideation.assemble.near_repo;

import com.example.ideation.assemble.type.AuthorId;
import com.example.ideation.assemble.type.AuthorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductAuthor {
//    private final long id;
    private final AuthorId authorId;
    private final String name;
    private final String nickname;
    private final AuthorType authorType;
}
