package br.dev.rodrigopinheioro.picpay.controller;

import br.dev.rodrigopinheioro.picpay.controller.dto.TransferDto;
import br.dev.rodrigopinheioro.picpay.entity.Transfer;
import br.dev.rodrigopinheioro.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {
        var wallet = transferService.transfer(dto);
        return ResponseEntity.ok(wallet);

    }
}
