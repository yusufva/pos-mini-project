package com.sigmaka.miniprojectpos.controller;

import com.sigmaka.miniprojectpos.dto.transactions.TransactionsDTO;
import com.sigmaka.miniprojectpos.dto.transactions.TransactionsResponseDTO;
import com.sigmaka.miniprojectpos.dto.transactionsdetail.TransactionsDetailResponseDTO;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.service.TransactionsService;
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
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping("listtransaksi")
    public ResponseEntity<GlobalHttpResponse<List<TransactionsResponseDTO>>> getAll(){
        var result = transactionsService.getAll();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("listtransaksidetail/{id}")
    public ResponseEntity<GlobalHttpResponse<List<TransactionsDetailResponseDTO>>> getDetail(@PathVariable(name = "id") int id){
        var result = transactionsService.getById(id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping("addtransaction")
    public ResponseEntity<GlobalHttpResponse<Object>> insert(@Valid @RequestBody TransactionsDTO transactionsDTO){
        var result = transactionsService.insert(transactionsDTO);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }
}
