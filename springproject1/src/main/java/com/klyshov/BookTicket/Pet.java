package com.klyshov.BookTicket;

/**
 * Created by 16688641 on 18.10.2019.
 */
public class Pet {
    private String ownerId;
    private String petId;

    public Pet(String petId, String ownerId) {
        this.petId = petId;
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }


    public String getPetId() {
        return petId;
    }


}
