/**
 * @author Vinhcv
 * @version 1.0
 * @since 2020-09-17
 */

package com.example.zktraining.model;

import com.example.zktraining.dto.MenuDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zk_function")
@Data
public class Function {
    @Id
    @Column(name = "function_id")
    private Integer functionId;

    @Column(name = "function_code")
    private String functionCode;

    @Column(name = "function_name")
    private String functionName;

    @Column(name = "function_path")
    private String functionPath;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Function parent;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.ALL)
    public List<Function> subMenus;

    @Column(name = "image_icon")
    private String imageIcon;

    @Column(name = "font_icon")
    private String fontIcon;

    @Column(name = "ord")
    private Integer ord;

    @Column(name = "status")
    private Boolean status;

    @Transient
    private int count;

    public List<Function> getSubMenus() {
        if (this.subMenus != null) {
            List<Function> lst = new ArrayList<>();
            for (Function sub:this.subMenus) {
                if (sub.getStatus() != null && sub.getStatus())
                    lst.add(sub);
            }
            this.subMenus = lst;
        }
        return this.subMenus;
    }

    public MenuDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        MenuDTO menuDTO = modelMapper.map(this, MenuDTO.class);
        return menuDTO;
    }

}