package com.example.ideation.assemble.type;

public class AuthorId {
    private final String id;

    public static AuthorId of(String id) {
        return new AuthorId(id);
    }
    public AuthorId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    //need some more methods
}
