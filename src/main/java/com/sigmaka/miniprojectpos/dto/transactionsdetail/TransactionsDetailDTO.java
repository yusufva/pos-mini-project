package com.sigmaka.miniprojectpos.dto.transactionsdetail;

import com.sigmaka.miniprojectpos.entity.ProductsEntity;
import com.sigmaka.miniprojectpos.entity.TransactionDetailsEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsDetailDTO {
    @NotNull(message = "product id cannot be null")
//    @NotEmpty(message = "product id cannot be empty")
    @Positive(message = "product id must be a positive number")
    private int productId;
    @NotNull(message = "quantity cannot be null")
//    @NotEmpty(message = "quantity cannot be empty")
    @Positive(message = "quantity must be a positive number")
    private int quantity;
    @NotNull(message = "subtotal cannot be null")
//    @NotEmpty(message = "subtotal cannot be empty")
    @Positive(message = "subtotal must be a positive number")
    private int subtotal;

    public TransactionDetailsEntity dtoToEntity(){
        var txDetail = new TransactionDetailsEntity();
        var product = new ProductsEntity();

        product.setId(productId);
        txDetail.setProduct(product);
        txDetail.setSubtotal(subtotal);
        txDetail.setQuantity(quantity);

        return txDetail;
    }
}
