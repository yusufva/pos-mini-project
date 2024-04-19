package com.sigmaka.miniprojectpos.dto.transactions;

import com.sigmaka.miniprojectpos.dto.transactionsdetail.TransactionsDetailDTO;
import com.sigmaka.miniprojectpos.entity.ProductsEntity;
import com.sigmaka.miniprojectpos.entity.TransactionDetailsEntity;
import com.sigmaka.miniprojectpos.entity.TransactionsEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDTO {
    @NotNull(message = "total amount cannot be null")
//    @NotEmpty(message = "total amount cannot be empty")
    @Positive(message = "total amount must be a positive number")
    private int totalAmount;
    @NotNull(message = "total pay cannot be null")
//    @NotEmpty(message = "total pay cannot be empty")
    @Positive(message = "total pay must be a positive number")
    private int totalPay;
    @NotNull(message = "transaction detail cannot be null")
    @NotEmpty(message = "transaction detail cannot be empty")
    private List<TransactionsDetailDTO> transactionsDetail;

    public TransactionsEntity dtoToEntity(){
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTotalAmount(totalAmount);
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transaction.setTotalPay(totalPay);

        return transaction;
    }
}
