package com.klyshov.lombok;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * Created by 16688641 on 30.10.2019.
 */
public class BuilderExample {
    public static void main(String[] args) {
        SomeObject someObject = SomeObject.builder().lastName("Klyshov").build();
        System.out.println(someObject);
    }
}
@ToString
@Builder
class SomeObject{
    @Builder.Default
    String name = "Di";
    @NonNull
    String lastName;
}