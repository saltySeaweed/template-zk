package com.example.zktraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabInfo implements Serializable{
    private Integer id;
    private int indexTab;
    private String path;
    private String title;
    private Boolean selected;
    private Boolean closeAble;
    private String iconsclass;

    public TabInfo(Integer id, String title, String path, String iconsclass) {
        super();
        this.id = id;
        this.title = title;
        this.path = path;
        this.iconsclass = iconsclass;
    }
}
