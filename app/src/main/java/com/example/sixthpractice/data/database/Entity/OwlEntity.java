package com.example.sixthpractice.data.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.sixthpractice.data.models.Owl;

@Entity(tableName = "owl_table")
public class OwlEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public String getType() {
        return type;
    }

    private String type;
    private int cover;

    public OwlEntity(@NonNull String name, String type, int cover) {
        this.name = name;
        this.type = type;
        this.cover = cover;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @NonNull
    public String getName() {
        return this.name;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public int getCover() {
        return cover;
    }
    public void setCover(int cover) {
        this.cover = cover;
    }
    public String getOwl() {
        return type;
    }
    public void setOwl(String owl) {
        this.type = owl;
    }
    public Owl toOwl(){
        return new Owl(this.name,this.type,this.cover);
    }
}





