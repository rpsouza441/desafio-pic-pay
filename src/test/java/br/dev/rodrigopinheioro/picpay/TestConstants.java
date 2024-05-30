package br.dev.rodrigopinheioro.picpay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.dev.rodrigopinheioro.picpay.controller.dto.TransferDto;
import br.dev.rodrigopinheioro.picpay.entity.Wallet;
import br.dev.rodrigopinheioro.picpay.entity.WalletType;

public class TestConstants {
  public static final List<Wallet> WALLETS = new ArrayList<>() {

    {
      add(new Wallet(500L, "Dean W", "123345", "dean@email.com", "123456", BigDecimal.valueOf(10),
          WalletType.Enum.USER.get()));
      add(new Wallet(501L, "Samuel W", "678901", "sam@email.com", "800900", BigDecimal.valueOf(30),
          WalletType.Enum.USER.get()));
      add(new Wallet(502L, "Castiel", "000001", "cas@email.com", "123", BigDecimal.valueOf(50),
          WalletType.Enum.MERCHANT.get()));
    }
  };
  public static final Wallet WALLET = WALLETS.get(0);

  public static final List<TransferDto> TRANSFERSDTO = new ArrayList<>() {
    {
      add(new TransferDto(BigDecimal.valueOf(5.00), WALLETS.get(0).getId(), WALLETS.get(1).getId()));
      add(new TransferDto(BigDecimal.valueOf(30.00), WALLETS.get(2).getId(), WALLETS.get(0).getId()));
      add(new TransferDto(BigDecimal.valueOf(10.00), 3L, 1L));
      add(new TransferDto(BigDecimal.valueOf(0.00), WALLETS.get(1).getId(), WALLETS.get(0).getId()));

    }
  };
  public static final TransferDto TRANSFERDTO = TRANSFERSDTO.get(0);
  public static final TransferDto TRANSFERDTONOWALLET = TRANSFERSDTO.get(2);
  public static final TransferDto TRANSFERDTOFROMMERCH = TRANSFERSDTO.get(1);
  public static final TransferDto TRANSFERDTONOVALUE = TRANSFERSDTO.get(3);

}
