package com.sigmaka.miniprojectpos.controller;

import com.sigmaka.miniprojectpos.dto.product.ProductsDTO;
import com.sigmaka.miniprojectpos.dto.product.ProductsResponseDTO;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.service.ProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pos/api/")
@Validated
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping("listproduct")
    public ResponseEntity<GlobalHttpResponse<List<ProductsResponseDTO>>> getAll(@RequestParam(required = false, name = "category_id") Integer id,
                                                                                @RequestParam(required = false) String title,
                                                                                @RequestParam(required = false, name = "sort_order") String sortOrder,
                                                                                @RequestParam(required = false, name = "sort_by") String sortBy
                                                                                ){
        if(id == null && title == null && sortOrder == null && sortBy == null){
            var result = productsService.getAll();
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
        }
        var result = productsService.getAllWithParams(id, title, sortOrder, sortBy);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("detailproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<ProductsResponseDTO>> getById(@PathVariable(name = "id") Integer id){
        var result = productsService.getById(id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping("addproduct")
    public ResponseEntity<GlobalHttpResponse<Object>> insert(@Valid @RequestBody ProductsDTO productsDTO){
        var result = productsService.insert(productsDTO);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PutMapping("updateproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<Object>> update(@Valid @RequestBody ProductsDTO productsDTO, @PathVariable(name = "id") Integer id){
        var result = productsService.update(id, productsDTO);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @DeleteMapping("deleteproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<Object>> delete(@PathVariable(name = "id") int id){
        var result = productsService.delete(id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }
}
