package com.learning.datawarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo {
    @NotNull
    private Integer art_id;
    @NotNull
    private Integer amount_of;

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public Integer getAmount_of() {
        return amount_of;
    }

    public void setAmount_of(Integer amount_of) {
        this.amount_of = amount_of;
    }
}
