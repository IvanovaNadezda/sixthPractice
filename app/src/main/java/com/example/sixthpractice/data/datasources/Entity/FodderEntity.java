package com.example.sixthpractice.data.datasources.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.sixthpractice.data.models.Fodder;

@Entity(tableName = "fodder_table")
public class FodderEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int photo;
    public FodderEntity(@NonNull String name, int photo) {
        this.name = name;
        this.photo = photo;
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

    public int getPhoto() {
        return photo;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public Fodder toFodder(){
        return new Fodder(this.name,this.photo);
    }
}






