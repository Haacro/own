package com.jk2102.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Relics {
    private Integer id;
    private String museum;
    private String name;
    private String inventory;
    private String size;
    private String details;
    private String era;
    private String technique;
    private String category;
    private String imageUrl;
    private String author;
    private String numberOfVolume;
    private String engravedOrHandcopied;
}
