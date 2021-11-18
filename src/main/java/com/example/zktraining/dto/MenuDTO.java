/**
 * @author  Vinhcv
 * @version 1.0
 * @since   2020-08-28
 */

package com.example.zktraining.dto;

import com.example.zktraining.model.Function;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDTO {
    private Integer functionId;
    private String functionCode;
    private List<MenuDTO> subMenus = new ArrayList<>();
    private String functionName;
    private String functionPath = NavigationDTO.BLANK_ZUL;
    private String imageIcon;
    private String fontIcon;
    private Integer ord;
    private Boolean status;
    private String rootUrl;

    private int counter;

    public MenuDTO() {
    }
    public MenuDTO(String labelKey) {
        this.functionCode = labelKey;
    }
    public MenuDTO(String labelKey, String icon) {
        this.functionCode = labelKey;
        this.fontIcon = icon;
    }

    public List<MenuDTO> getSubMenus() {
        if (subMenus != null) {
            counter = subMenus.size();
        }
        return subMenus;
    }

    public Function toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        Function menu = modelMapper.map(this, Function.class);

        return menu;
    }

}
