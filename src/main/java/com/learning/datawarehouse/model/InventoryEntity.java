package com.learning.datawarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="inventories")
public class InventoryEntity {
    @Id
    private int artId;

    private String artName;

    private int stock;


}

