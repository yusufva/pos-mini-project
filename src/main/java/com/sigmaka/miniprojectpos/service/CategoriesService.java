package com.sigmaka.miniprojectpos.service;

import com.sigmaka.miniprojectpos.dto.category.CategoriesDTO;
import com.sigmaka.miniprojectpos.dto.category.CategoryResponseDTO;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.repository.CategoriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepo categoriesRepo;

    public GlobalHttpResponse<List<CategoryResponseDTO>> getAll(){
        var categories = categoriesRepo.findAll();
        List<CategoryResponseDTO> result = new ArrayList<>();
        for(var category : categories){
            result.add(category.entityToDto());
        }

        return new GlobalHttpResponse<>(200, "success retrieve data", result);
    }

    public GlobalHttpResponse<CategoryResponseDTO> getById(int id){
        var category = categoriesRepo.findById(id).orElse(null);
        if(category == null){
            return new GlobalHttpResponse<>(404, "category not found", new CategoryResponseDTO());
        }

        return new GlobalHttpResponse<>(200, "success getting category data wit id "+id, category.entityToDto());
    }

    public GlobalHttpResponse<Object> insert(CategoriesDTO categoriesDTO){
        var category = categoriesDTO.dtoToEntity();
        var result = categoriesRepo.save(category);
        return new GlobalHttpResponse<>(201, "success create data", new ArrayList<>());
    }

    public GlobalHttpResponse<Object> update(int id, CategoriesDTO categoriesDTO){
        var category = categoriesRepo.findById(id).orElse(null);
        if(category == null){
            return new GlobalHttpResponse<>(404, "category not found", null);
        }

        category.setName(categoriesDTO.getName());
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        categoriesRepo.save(category);

        return new GlobalHttpResponse<>(200, "success update data", new ArrayList<>());
    }

    public GlobalHttpResponse<Object> delete(int id){
        var category = categoriesRepo.findById(id).orElse(null);
        if(category == null){
            return new GlobalHttpResponse<>(404, "category not found", null);
        }

        categoriesRepo.deleteById(id);
        return new GlobalHttpResponse<>(200, "success delete data", new ArrayList<>());
    }
}
