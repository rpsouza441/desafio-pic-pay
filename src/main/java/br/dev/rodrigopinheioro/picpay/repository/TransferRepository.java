package br.dev.rodrigopinheioro.picpay.repository;

import br.dev.rodrigopinheioro.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
