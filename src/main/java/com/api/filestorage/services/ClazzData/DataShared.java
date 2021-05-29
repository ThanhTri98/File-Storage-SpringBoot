package com.api.filestorage.services.ClazzData;

public class DataShared {
    private int id;
    private String file_sk;
    private String name;
    private long size;
    private String extension;
    private String parent;
    private String owner;
    private String kind; // (music, video, picture)

    public DataShared() {
    }

    public DataShared(int id, String file_sk, String name, long size, String extension, String parent, String owner,
            String kind) {
        this.id = id;
        this.file_sk = file_sk;
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.parent = parent;
        this.owner = owner;
        this.kind = kind;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_sk() {
        return this.file_sk;
    }

    public void setFile_sk(String file_sk) {
        this.file_sk = file_sk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
