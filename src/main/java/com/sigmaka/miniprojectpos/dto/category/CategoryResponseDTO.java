package com.sigmaka.miniprojectpos.dto.category;

import com.sigmaka.miniprojectpos.dto.product.ProductsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private int id;
    private String name;
    private List<ProductsResponseDTO> products;
}
