package br.dev.rodrigopinheioro.picpay.service;

import br.dev.rodrigopinheioro.picpay.controller.dto.TransferDto;
import br.dev.rodrigopinheioro.picpay.entity.Transfer;
import br.dev.rodrigopinheioro.picpay.entity.Wallet;
import br.dev.rodrigopinheioro.picpay.exception.InsufficientBallanceException;
import br.dev.rodrigopinheioro.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.dev.rodrigopinheioro.picpay.exception.TransferNotAuthorizedException;
import br.dev.rodrigopinheioro.picpay.exception.WalletNotFoundException;
import br.dev.rodrigopinheioro.picpay.repository.TransferRepository;
import br.dev.rodrigopinheioro.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());
        var transfer= new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));


        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }
        if (!sender.isBalanceEqualOrGreaterThan(transferDto.value())) {
            throw new InsufficientBallanceException(transferDto.value());
        }

        if(!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();

        }

    }
}
