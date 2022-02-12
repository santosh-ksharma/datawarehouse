package com.learning.datawarehouse.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryInfo {

    //Property names kept in sync with property name in file for automatic transformation from json da
    private Integer art_id;
    private String name;
    private String stock;

}
