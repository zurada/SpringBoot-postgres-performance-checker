package com.azurada.service;

import com.azurada.aspect.LogExecutionTime;
import com.azurada.model.*;
import com.azurada.repository.TransactionWithLongRepository;
import com.azurada.repository.TransactionWithStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
//@Transactional left intentionally not transactional
public class PerformanceService {

    private final TransactionWithLongRepository transactionWithLongRepo;

    private final TransactionWithStringRepository transactionWithStringRepo;

    @Autowired
    public PerformanceService(TransactionWithLongRepository transactionWithLongRepo, TransactionWithStringRepository transactionWithStringRepo) {
        this.transactionWithLongRepo = transactionWithLongRepo;
        this.transactionWithStringRepo = transactionWithStringRepo;
    }

    /**
     * Inserts new transactions
     *
     * @param count quantity of transactions
     * @return list of Ids from transactionWithStringRepository
     */
    @LogExecutionTime
    public List<String> insertTransactions(int count) {
        List<String> listOfStringIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            transactionWithLongRepo.save(buildTransactionWithLong());
            listOfStringIds.add(transactionWithStringRepo.save(buildTransactionWithString()).getId());
        }
        return listOfStringIds;
    }

    /**
     * Checks finding transaction by LONG type id execution time
     *
     * @param id to search for
     * @return transaction
     */
    @LogExecutionTime
    public TransactionWithLong findOneById(Long id) {
        return transactionWithLongRepo.findOne(id);
    }

    /**
     * Checks finding transaction by STRING type id execution time
     *
     * @param id to search for
     * @return transaction
     */
    @LogExecutionTime
    public TransactionWithString findOneById(String id) {
        return transactionWithStringRepo.findOne(id);
    }

    /**
     * Called first time before testing execution time to make sure that entityManager is already connected to the db
     */
    public void ignoreFirstFinding() {
        transactionWithLongRepo.findOne(1L);
        transactionWithStringRepo.findOne("no_id");
    }

    /**
     * Builds example Transaction With Long as ID
     *
     * @return new object
     */
    private TransactionWithLong buildTransactionWithLong() {
        return TransactionWithLong.builder()
                .agentTransactionId(UUID.randomUUID().toString())
                .amount(BigDecimal.valueOf(new Random().nextDouble()))
                .commission(BigDecimal.valueOf(new Random().nextDouble()))
                .creationDate(LocalDateTime.now())
                .cost(BigDecimal.valueOf(new Random().nextDouble()))
                .currency(Currency.USD)
                .paymentProvider(PaymentProvider.PAYPAL)
                .senderEmail(UUID.randomUUID().toString())
                .receiverEmail(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.CREATED)
                .transactionType(TransactionType.EXCHANGE)
                .walletId(UUID.randomUUID().toString())
                .build();
    }

    /**
     * Builds example Transaction With String as ID
     *
     * @return new object
     */
    private TransactionWithString buildTransactionWithString() {
        return TransactionWithString.builder()
                .agentTransactionId(UUID.randomUUID().toString())
                .amount(BigDecimal.valueOf(new Random().nextDouble()))
                .commission(BigDecimal.valueOf(new Random().nextDouble()))
                .creationDate(LocalDateTime.now())
                .cost(BigDecimal.valueOf(new Random().nextDouble()))
                .currency(Currency.USD)
                .paymentProvider(PaymentProvider.PAYU)
                .senderEmail(UUID.randomUUID().toString())
                .receiverEmail(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.CREATED)
                .transactionType(TransactionType.EXCHANGE)
                .walletId(UUID.randomUUID().toString())
                .build();
    }

}
