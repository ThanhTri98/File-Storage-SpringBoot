package com.api.filestorage.services.ClazzData;

import java.util.List;

public class TrashOrUnTrash {
    private String creator;
    private int state = 0;
    private String kind; // videos, musics, pictures
    private List<Data> datas;

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Data> getDatas() {
        return this.datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data {
        private int id;
        private String name;
        private String extension;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return this.extension;
        }
    }
}
