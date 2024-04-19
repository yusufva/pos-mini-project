package com.sigmaka.miniprojectpos.controller;

import com.sigmaka.miniprojectpos.dto.category.CategoriesDTO;
import com.sigmaka.miniprojectpos.dto.category.CategoryResponseDTO;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.service.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pos/api/")
@Validated
public class CategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping("listcategory")
    public ResponseEntity<GlobalHttpResponse<List<CategoryResponseDTO>>> getAll(){
        var result = categoriesService.getAll();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping("addcategory")
    public ResponseEntity<GlobalHttpResponse<Object>> insert(@Valid @RequestBody CategoriesDTO categoriesDTO){
        var result = categoriesService.insert(categoriesDTO);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PutMapping("editcategory/{id}")
    public ResponseEntity<GlobalHttpResponse<Object>> update(@PathVariable(name = "id") int id, @Valid @RequestBody CategoriesDTO categoriesDTO){
            var result = categoriesService.update(id, categoriesDTO);
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @DeleteMapping("deletecategory/{id}")
    public ResponseEntity<GlobalHttpResponse<Object>> delete(@PathVariable(name = "id") int id){
            var result = categoriesService.delete(id);
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }
}
