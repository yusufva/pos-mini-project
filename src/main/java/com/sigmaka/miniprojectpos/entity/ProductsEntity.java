package com.sigmaka.miniprojectpos.entity;

import com.sigmaka.miniprojectpos.dto.category.CategoriesDTO;
import com.sigmaka.miniprojectpos.dto.product.ProductsResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "products", schema = "public", catalog = "pos-project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;
    @Basic
    @Column(name = "image", nullable = true, length = 255)
    private String image;
    @Basic
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoriesEntity category;
    @OneToMany(mappedBy = "product")
    private List<TransactionDetailsEntity> transactionDetails;

    public ProductsResponseDTO entityToDto(){
        CategoriesDTO categories = new CategoriesDTO();
        categories.setName(category.getName());
        ProductsResponseDTO products = new ProductsResponseDTO(getId(), getTitle(), getPrice(), getImage(), categories);

        return products;
    }
}
