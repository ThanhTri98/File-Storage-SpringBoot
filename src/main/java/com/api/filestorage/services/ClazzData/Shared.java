package com.api.filestorage.services.ClazzData;

import java.util.List;

public class Shared {
    private String owner;
    private List<String> receivers;
    private List<Integer> file_ids;

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getReceivers() {
        return this.receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public List<Integer> getFile_ids() {
        return this.file_ids;
    }

    public void setFile_ids(List<Integer> file_ids) {
        this.file_ids = file_ids;
    }

}
