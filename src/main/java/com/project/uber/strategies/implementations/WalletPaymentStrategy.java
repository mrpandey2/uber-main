package com.project.uber.strategies.implementations;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.services.WalletService;
import com.project.uber.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    public WalletPaymentStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver= payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        double paymentAddedToWallet = payment.getAmount() - (payment.getAmount() * PLATFORM_FEE);

        walletService.addMoneyToWallet(driver.getUser(),
                paymentAddedToWallet,
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

        walletService.subtractMoneyFromWallet(rider.getUser(),
                payment.getAmount(),
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

    }
}
