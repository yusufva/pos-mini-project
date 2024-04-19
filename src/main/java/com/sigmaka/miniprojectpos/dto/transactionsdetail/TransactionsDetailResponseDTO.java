package com.sigmaka.miniprojectpos.dto.transactionsdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDetailResponseDTO {
    private int id;
    private int transactionId;
    private int productId;
    private int quantity;
    private int subtotal;
}
