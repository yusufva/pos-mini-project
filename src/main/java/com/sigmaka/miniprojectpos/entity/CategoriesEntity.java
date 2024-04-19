package com.sigmaka.miniprojectpos.entity;

import com.sigmaka.miniprojectpos.dto.category.CategoryResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "categories", schema = "public", catalog = "pos-project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "category")
    private List<ProductsEntity> products;

    public CategoryResponseDTO entityToDto(){
        CategoryResponseDTO category = new CategoryResponseDTO();

        category.setId(id);
        category.setName(name);

        return category;
    }
}
