package com.learning.datawarehouse.upload;
import com.learning.datawarehouse.model.InventoryEntity;
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

}

