package com.learning.datawarehouse.util;
import com.learning.datawarehouse.model.InventoryEntity;
import com.learning.datawarehouse.dto.InventoryInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {

    public static InventoryMapper INSTANCE = Mappers.getMapper( InventoryMapper.class );

    @Mapping(source = "art_id", target = "artId")
    @Mapping(source = "name", target = "artName")
    @Mapping(source = "stock", target = "stock")
    InventoryEntity toInventoryEntity(InventoryInfo inventoryInfo);

    @Mapping(source = "artId", target = "art_id")
    @Mapping(source = "artName", target = "name")
    @Mapping(source = "stock", target = "stock")
    InventoryInfo toInventoryDTO(InventoryEntity inventoryEntity);

}

