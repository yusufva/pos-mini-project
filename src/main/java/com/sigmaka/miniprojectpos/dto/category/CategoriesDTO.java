package com.sigmaka.miniprojectpos.dto.category;

import com.sigmaka.miniprojectpos.entity.CategoriesEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {
    @NotBlank(message = "name field cannot be blank")
    @NotNull(message = "name field cannot be null")
    @Size(max = 255, message = "name maximum size is 255 character")
    private String name;

    public CategoriesEntity dtoToEntity(){
        CategoriesEntity category =  new CategoriesEntity();

        category.setName(name);
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return category;
    }
}
