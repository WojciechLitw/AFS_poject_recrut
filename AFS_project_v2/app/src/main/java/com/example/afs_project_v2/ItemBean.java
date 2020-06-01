package com.example.afs_project_v2;

public class ItemBean {

    public long id;
    public int status;
    public String name;

    public ItemBean(){
    }

    public ItemBean(long id, String name, int status){
        this.status = status;
        this.name = name;
        this.id = id;
    }

    public String toString(){
        return id + " " + name + " " + status;
    }
}
