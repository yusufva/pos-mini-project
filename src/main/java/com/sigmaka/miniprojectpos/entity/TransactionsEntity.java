package com.sigmaka.miniprojectpos.entity;

import com.sigmaka.miniprojectpos.dto.transactions.TransactionsResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "transactions", schema = "public", catalog = "pos-project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "transaction_date", nullable = false)
    private Timestamp transactionDate;
    @Basic
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;
    @Basic
    @Column(name = "total_pay", nullable = false)
    private Integer totalPay;
    @OneToMany(mappedBy = "transactions")
    private List<TransactionDetailsEntity> transactionDetails;

    public TransactionsResponseDTO entityToDto(){
        var transaction = new TransactionsResponseDTO();

        transaction.setId(id);
        transaction.setTransactionDate(transactionDate);
        transaction.setTotalAmount(totalAmount);
        transaction.setTotalPay(totalPay);

        return transaction;
    }
}
