package br.dev.rodrigopinheioro.picpay.service;

import br.dev.rodrigopinheioro.picpay.controller.dto.WalletDTO;
import br.dev.rodrigopinheioro.picpay.entity.Wallet;
import br.dev.rodrigopinheioro.picpay.exception.WalletDataAlreadyExistsException;
import br.dev.rodrigopinheioro.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(WalletDTO walletDTO) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(walletDTO.cpfCnpj(), walletDTO.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(walletDTO.toWallet());
    }
}
