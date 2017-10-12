package com.azurada.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionWithString {
    /**
     * TransactionWithString identifier
     */
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2", parameters = {@Parameter(
            name = "uuid_gen_strategy_class",
            value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(updatable = false, nullable = false)
    private String id;
    /**
     * Address email of transaction sender
     */
    private String senderEmail;
    /**
     * Address email of transaction receiver
     */
    private String receiverEmail;
    /**
     * Amount being transferred
     */
    @Column(precision = 20, scale = 8)
    private BigDecimal amount;
    /**
     * Currency of amount being transferred
     */
    @Enumerated(EnumType.STRING)
    private Currency currency;
    /**
     * TransactionWithString status
     */
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    /**
     * TransactionWithString type
     */
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    /**
     * Payment provider
     */
    @Enumerated(EnumType.STRING)
    private PaymentProvider paymentProvider;
    /**
     * Creation date of TransactionWithString entity in database
     */
    private LocalDateTime creationDate;
    /**
     * Date of transaction in transaction agent's system
     */
    private LocalDateTime transactionDate;
    /**
     * Commission earning deduced from transaction amount and/or cost in our system
     */
    @Column(precision = 20, scale = 8)
    private BigDecimal commission;
    /**
     * TransactionWithString identifier in transaction agent's system
     */
    private String agentTransactionId;

    /**
     * Identifier of wallet being associated with transaction
     */

    //private String wallet;
    private String walletId;

    /**
     * Amount that user paid for the transaction (in USD)
     */
    @Column(precision = 20, scale = 8)
    private BigDecimal cost;

    /**
     * Amount converted to double used for filtering data i.e. comparing amounts in queries
     */
    private double amountAsDouble;

    /**
     * Commission converted to double used for filtering data i.e. comparing amounts in queries
     */
    private double commissionAsDouble;

    /**
     * Overriding Lombok's setter, so that setting amount would automatically set amountAsDouble
     *
     * @param amount TransactionWithString amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        this.amountAsDouble = amount.doubleValue();
    }

    /**
     * Overriding Lombok's setter, so that setting commission would automatically set amountAsDouble
     *
     * @param commission Commission amount
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
        this.commissionAsDouble = commission.doubleValue();
    }

    /**
     * Overriding Lombok's @Builder, so that setting amount and commission would automatically set amountAsDouble and commissionAsDouble
     */
    public static class TransactionBuilder {
        private double amountAsDouble;
        private double commissionAsDouble;
        private BigDecimal amount;
        private BigDecimal commission;

        public TransactionBuilder amount(BigDecimal amount) {
            this.amount = amount;
            this.amountAsDouble = amount.doubleValue();
            return this;
        }

        public TransactionBuilder commission(BigDecimal commission) {
            this.commission = commission;
            this.commissionAsDouble = commission.doubleValue();
            return this;
        }
    }
}
