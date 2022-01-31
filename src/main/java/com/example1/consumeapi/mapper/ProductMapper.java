package com.example1.consumeapi.mapper;
import java.util.List;

import com.example1.consumeapi.dto.ProductDTO;
import com.example1.consumeapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper
{
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);
}