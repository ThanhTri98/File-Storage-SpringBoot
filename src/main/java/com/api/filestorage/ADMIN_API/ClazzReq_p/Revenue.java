package com.api.filestorage.ADMIN_API.ClazzReq_p;

public class Revenue {
    private String username;
    private String name;
    private long totalAmount;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Revenue() {
    }


    public Revenue(String username, String name, long totalAmount) {
        this.username = username;
        this.name = name;
        this.totalAmount = totalAmount;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

}
