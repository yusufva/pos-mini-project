package com.sigmaka.miniprojectpos.service;

import com.sigmaka.miniprojectpos.dto.product.ProductsDTO;
import com.sigmaka.miniprojectpos.dto.product.ProductsResponseDTO;
import com.sigmaka.miniprojectpos.entity.ProductsEntity;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.repository.CategoriesRepo;
import com.sigmaka.miniprojectpos.repository.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepo productsRepo;
    private final CategoriesRepo categoriesRepo;

    public GlobalHttpResponse<List<ProductsResponseDTO>> getAll(){
        List<ProductsEntity> products = productsRepo.findAll();
        List<ProductsResponseDTO> result = new ArrayList<>();
        for(ProductsEntity product : products){
            result.add(product.entityToDto());
        }

        return new GlobalHttpResponse<>(200, "success retrieve data", result);
    }

    public GlobalHttpResponse<ProductsResponseDTO> getById(int id){
        var product = productsRepo.findById(id).orElse(null);
        if(product == null){
            return new GlobalHttpResponse<>(404, "products not found", new ProductsResponseDTO());
        }

        return new GlobalHttpResponse<>(200, "success getting product with id "+id, product.entityToDto());
    }

    public GlobalHttpResponse<List<ProductsResponseDTO>> getAllWithParams(Integer id, String title, String sortOrder, String sortBy){
        try{
            if(id == null && title == null){
                Sort sort = Sort.by(Sort.Direction.fromString(sortOrder.toUpperCase()), sortBy);
                var products = productsRepo.findAll(sort);
                List<ProductsResponseDTO> result = new ArrayList<>();
                for(var product: products){
                    result.add(product.entityToDto());
                }

                return new GlobalHttpResponse<>(200, "success retrieve data", result);
            } else if (id == null) {
                Sort sort = Sort.by(Sort.Direction.fromString(sortOrder.toUpperCase()), sortBy);
                var products = productsRepo.findByTitleContainingIgnoreCase(title, sort);
                if(products.isEmpty()){
                    return new GlobalHttpResponse<>(404, "no products with name "+title, new ArrayList<>());
                }
                List<ProductsResponseDTO> result = new ArrayList<>();
                for(var product : products){
                    result.add(product.entityToDto());
                }

                return new GlobalHttpResponse<>(200, "success retrieve data", result);
            }

            var products = productsRepo.findByCategory_Id(id);
            if(products.isEmpty()){
                return new GlobalHttpResponse<>(404, "product with this category not found", new ArrayList<>());
            }
            List<ProductsResponseDTO> result = new ArrayList<>();
            for(var product : products){
                result.add(product.entityToDto());
            }
            return new GlobalHttpResponse<>(HttpStatus.OK.value(), "success retrieve data", result);
        } catch (Exception e){
            return new GlobalHttpResponse<>(400, "failed getting data", new ArrayList<>());
        }
    }

    public GlobalHttpResponse<Object> insert(ProductsDTO productsDTO){
        var product = productsDTO.dtoToEntity();
        var category = categoriesRepo.findById(productsDTO.getCategoryId()).orElse(null);
        if(category == null){
            return new GlobalHttpResponse<>(400, "Category not found", null);
        }
        product.setCategory(category);
        var result = productsRepo.save(product);
        return new GlobalHttpResponse<>(201, "success insert data", new ArrayList<>());
    }

    public GlobalHttpResponse<Object> update(int id, ProductsDTO productsDTO){
        var product = productsRepo.findById(id).orElse(null);
        if(product == null){
            return new GlobalHttpResponse<>(404, "product not found", null);
        }
        var category = categoriesRepo.findById(productsDTO.getCategoryId()).orElse(null);
        if(category == null){
            return new GlobalHttpResponse<>(404, "category not found", null);
        }

        product.setTitle(productsDTO.getTitle());
        product.setImage(productsDTO.getImages());
        product.setPrice(productsDTO.getPrice());
        product.setCategory(category);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productsRepo.save(product);
        return new GlobalHttpResponse<>(200, "success update data", new ArrayList<>());
    }

    public GlobalHttpResponse<Object> delete(int id){
        var product = productsRepo.findById(id).orElse(null);
        if(product == null){
            return new GlobalHttpResponse<>(404, "product not found", null);
        }
        productsRepo.deleteById(id);
        return new GlobalHttpResponse<>(200, "product with id "+id+" has been deleted", new ArrayList<>());
    }
}
