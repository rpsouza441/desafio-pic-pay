package br.dev.rodrigopinheioro.picpay.repository;

import br.dev.rodrigopinheioro.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
