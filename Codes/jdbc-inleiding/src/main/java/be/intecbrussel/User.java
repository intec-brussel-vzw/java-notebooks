package be.intecbrussel;

import lombok.Data;

import java.util.Objects;

@Data
public class User {

    private int userId;

    private String email;

    private String password;

}
