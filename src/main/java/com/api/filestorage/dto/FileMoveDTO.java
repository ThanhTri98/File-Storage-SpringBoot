package com.api.filestorage.dto;

import java.util.List;

public class FileMoveDTO {
    private String new_parent;
    private String creator;
    private List<Data> datas;
    private int type_copy_move;

    @Override
    public String toString() {
        return "{" + " new_parent='" + getNew_parent() + "'" + ", creator='" + getCreator() + "'" + ", datas='"
                + getDatas() + "'" + ", is_replace='" + getType_copy_move() + "'" + "}";
    }


    public int getType_copy_move() {
        return this.type_copy_move;
    }

    public void setType_copy_move(int type_copy_move) {
        this.type_copy_move = type_copy_move;
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

        @Override
        public String toString() {
            return "id " + id + ", name " + name + ", " + extension;
        }

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
