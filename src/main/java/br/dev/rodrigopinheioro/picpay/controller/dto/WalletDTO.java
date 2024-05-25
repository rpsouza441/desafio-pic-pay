package br.dev.rodrigopinheioro.picpay.controller.dto;

import br.dev.rodrigopinheioro.picpay.entity.Wallet;
import br.dev.rodrigopinheioro.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletDTO(@NotBlank String fullName,
                        @NotBlank String cpfCnpj,
                        @NotBlank String email,
                        @NotBlank String password,
                        @NotNull WalletType.Enum walletType
) {

    public Wallet toWallet() {
        return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
    }
}
