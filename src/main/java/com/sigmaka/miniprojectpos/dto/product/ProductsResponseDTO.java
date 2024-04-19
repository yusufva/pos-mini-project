package com.sigmaka.miniprojectpos.dto.product;

import com.sigmaka.miniprojectpos.dto.category.CategoriesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponseDTO {
    private int id;
    private String title;
    private int price;
    private String images;
    private CategoriesDTO category;

}
