package com.sigmaka.miniprojectpos.service;

import com.sigmaka.miniprojectpos.dto.transactions.TransactionsDTO;
import com.sigmaka.miniprojectpos.dto.transactions.TransactionsResponseDTO;
import com.sigmaka.miniprojectpos.dto.transactionsdetail.TransactionsDetailResponseDTO;
import com.sigmaka.miniprojectpos.entity.TransactionDetailsEntity;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.repository.ProductsRepo;
import com.sigmaka.miniprojectpos.repository.TransactionsDetailRepo;
import com.sigmaka.miniprojectpos.repository.TransactionsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepo transactionsRepo;
    private final TransactionsDetailRepo transactionsDetailRepo;
    private final ProductsRepo productsRepo;

    public GlobalHttpResponse<List<TransactionsResponseDTO>> getAll(){
        var transactions = transactionsRepo.findAll();
        List<TransactionsResponseDTO> result = new ArrayList<>();
        for(var transaction : transactions){
            result.add(transaction.entityToDto());
        }

        return new GlobalHttpResponse<>(200, "success retrieve data", result);
    }

    public GlobalHttpResponse<List<TransactionsDetailResponseDTO>> getById(int id){
        var transaction = transactionsRepo.findById(id).orElse(null);
        if(transaction == null){
            return new GlobalHttpResponse<>(404, "data not found", null);
        }
        List<TransactionsDetailResponseDTO> txDetail = new ArrayList<>();
        for(var txd : transaction.getTransactionDetails()){
            txDetail.add(txd.entityToDto());
        }
        return new GlobalHttpResponse<>(200, "success retrieve data", txDetail);
    }

    @Transactional
    public GlobalHttpResponse<Object> insert(TransactionsDTO transactionsDTO){
        for(var data: transactionsDTO.getTransactionsDetail()){
            var product = productsRepo.findById(data.getProductId()).orElse(null);
            if(product == null){
                return new GlobalHttpResponse<>(404, "product not found", null);
            }
            int realSub = data.getQuantity() * product.getPrice();
            if (realSub != data.getSubtotal()) return new GlobalHttpResponse<>(400, "sub total is invalid", new ArrayList<>());
        }

        int sumTotal = 0;
        for(var data : transactionsDTO.getTransactionsDetail()){
            sumTotal += data.getSubtotal();
        }
        if(transactionsDTO.getTotalAmount() != sumTotal) return new GlobalHttpResponse<>(400, "Total Amount invalid", new ArrayList<>());

        if(transactionsDTO.getTotalPay() < transactionsDTO.getTotalAmount()) return new GlobalHttpResponse<>(400, "Total Pay cannot be less than Total Amount", new ArrayList<>());

        var transaction = transactionsDTO.dtoToEntity();
        List<TransactionDetailsEntity> txDetail = new ArrayList<>();
        for(var transactionDetail : transactionsDTO.getTransactionsDetail()){
            var product = productsRepo.findById(transactionDetail.getProductId()).orElse(null);
            if(product == null){
                return new GlobalHttpResponse<>(404, "product not found", null);
            }
            var txsdetail = transactionDetail.dtoToEntity();
            txsdetail.setProduct(product);
            txDetail.add(txsdetail);
        }

        transaction.setTransactionDetails(txDetail);
        var tx = transactionsRepo.save(transaction);
        for(var txsd : txDetail){
            txsd.setTransactions(tx);
        }
        transactionsDetailRepo.saveAll(txDetail);

        return new GlobalHttpResponse<>(201, "success create data", new ArrayList<>());
    }

}
