package org.example.model;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    private int id;
    @SerializedName("petId")
    private int petId;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("shipDate")
    private String shipDate;
    @SerializedName("status")
    private String status;
    @SerializedName("complete")
    private Boolean complete;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }
    public void setShipDate(String shipDate) {
        shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        status = status;
    }

    public Boolean getComplete() {
        return complete;
    }
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString(){
        return
                "Pet{" +
                        "id = '" + id + '\'' +
                        ",petId = '" + petId + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",shipDate = '" + shipDate + '\'' +
                        ",status = '" + status + '\'' +
                        ",complete = '" + status + '\'' +
                        "}";
    }
}