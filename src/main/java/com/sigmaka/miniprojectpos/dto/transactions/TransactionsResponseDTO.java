package com.sigmaka.miniprojectpos.dto.transactions;

import com.sigmaka.miniprojectpos.dto.transactionsdetail.TransactionsDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponseDTO {
    private int id;
    private Timestamp transactionDate;
//    private List<TransactionsDetailResponseDTO> transactionDetails;
    private int totalAmount;
    private int totalPay;
}
