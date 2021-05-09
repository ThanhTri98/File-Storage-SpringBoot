package com.api.filestorage.dto;

import java.util.List;

public class FileMoveDTO {
    private String new_parent;
    private String creator;
    private List<Data> datas;
    private boolean is_replace;

    public boolean getIs_replace() {
        return this.is_replace;
    }

    public void setIs_replace(boolean is_replace) {
        this.is_replace = is_replace;
    }

    public List<Data> getDatas() {
        return this.datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getNew_parent() {
        return this.new_parent;
    }

    public void setNew_parent(String new_parent) {
        this.new_parent = new_parent;
    }

    public static class Data {
        private int id;
        private String name;
        private String extension;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExtension() {
            return this.extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

    }

}
