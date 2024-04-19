package com.sigmaka.miniprojectpos.entity;

import com.sigmaka.miniprojectpos.dto.transactionsdetail.TransactionsDetailResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_details", schema = "public", catalog = "pos-project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Basic
    @Column(name = "subtotal", nullable = false)
    private Integer subtotal;
    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionsEntity transactions;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductsEntity product;

    public TransactionsDetailResponseDTO entityToDto(){
        TransactionsDetailResponseDTO txDetail = new TransactionsDetailResponseDTO();

        txDetail.setId(id);
        txDetail.setTransactionId(transactions.getId());
        txDetail.setQuantity(quantity);
        txDetail.setSubtotal(subtotal);
        txDetail.setProductId(product.getId());

        return txDetail;
    }
}
