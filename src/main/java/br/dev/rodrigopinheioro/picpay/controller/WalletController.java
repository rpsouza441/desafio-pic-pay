package br.dev.rodrigopinheioro.picpay.controller;

import br.dev.rodrigopinheioro.picpay.controller.dto.WalletDTO;
import br.dev.rodrigopinheioro.picpay.entity.Wallet;
import br.dev.rodrigopinheioro.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid WalletDTO walletDTO){
        var wallet=walletService.createWallet(walletDTO);

        return  ResponseEntity.ok(wallet);
    }
}
