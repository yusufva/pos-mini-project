package com.sigmaka.miniprojectpos.dto.product;

import com.sigmaka.miniprojectpos.entity.CategoriesEntity;
import com.sigmaka.miniprojectpos.entity.ProductsEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    @Size(max = 255, message = "title maximum size is 255 character")
    private String title;
    @NotNull(message = "price cannot be null")
//    @NotEmpty(message = "price cannot be empty")
    @Positive(message = "price must be a positive number")
    private int price;
    @NotNull(message = "category id cannot be null")
//    @NotEmpty(message = "category id cannot be empty")
    @Positive(message = "price must be a positive number")
    private int categoryId;
    @Size(max = 255, message = "images maximum size is 255 character")
    private String images;

    public ProductsEntity dtoToEntity(){
        ProductsEntity product = new ProductsEntity();
        CategoriesEntity categories = new CategoriesEntity();
        categories.setId(categoryId);

        product.setTitle(title);
        product.setPrice(price);
        product.setImage(images);
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return product;
    }
}
