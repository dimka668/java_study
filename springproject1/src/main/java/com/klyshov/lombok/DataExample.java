package com.klyshov.lombok;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by 16688641 on 30.10.2019.
 */
public class DataExample {
    public static void main(String[] args) {
        User user = new User("Dima", "Klyshov");
        System.out.println(user);
    }
}

@Data
class User {
    @NonNull
    String name;
    @NonNull
    String lastName;
}
