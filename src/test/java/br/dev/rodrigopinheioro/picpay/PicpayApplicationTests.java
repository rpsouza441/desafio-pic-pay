package br.dev.rodrigopinheioro.picpay;

import br.dev.rodrigopinheioro.picpay.controller.dto.WalletDTO;
import br.dev.rodrigopinheioro.picpay.entity.WalletType;
import br.dev.rodrigopinheioro.picpay.repository.WalletRepository;

import static br.dev.rodrigopinheioro.picpay.TestConstants.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class PicpayApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private WalletRepository walletRepository;

	@Test
	void testCreateWalletSuccess() {
		var wallet = new WalletDTO("Pedrinho Z", "123", "pz@email.com", "123456", WalletType.Enum.USER);

		webTestClient
				.post()
				.uri("/wallets")
				.bodyValue(wallet)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("fullName").isEqualTo(wallet.fullName())
				.jsonPath("cpfCnpj").isEqualTo(wallet.cpfCnpj())
				.jsonPath("email").isEqualTo(wallet.email())
				.jsonPath("balance").isEqualTo(BigDecimal.ZERO)
				.jsonPath("password").isEqualTo(wallet.password())
				.jsonPath("walletType").isEqualTo(wallet.walletType().get());

	}

	@Test
	void testCreateWalletFailure() {
		var dto = new WalletDTO("", "", "", "", null);
		webTestClient
				.post()
				.uri("/wallets")
				.bodyValue(dto)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	@Sql("/import.sql")
	void testTransferWalletSuccess() {

		webTestClient
				.post()
				.uri("/transfer")
				.bodyValue(TRANSFERDTO)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("sender.id").isEqualTo(WALLETS.get(0).getId())
				.jsonPath("sender.fullName").isEqualTo(WALLETS.get(0).getFullName())
				.jsonPath("sender.balance").isEqualTo(5)
				.jsonPath("sender.walletType.description").isEqualTo(WALLETS.get(0).getWalletType().getDescription())
				.jsonPath("receiver.id").isEqualTo(WALLETS.get(1).getId())
				.jsonPath("receiver.fullName").isEqualTo(WALLETS.get(1).getFullName())
				.jsonPath("receiver.balance").isEqualTo(35)
				.jsonPath("receiver.walletType.description").isEqualTo(WALLETS.get(1).getWalletType().getDescription())
				.jsonPath("value").isEqualTo(TRANSFERDTO.value())

		;

	}

	@Test
	@Sql("/import.sql")
	void testTransferWalletNoWalletFailure() {

		webTestClient
				.post()
				.uri("/transfer")
				.bodyValue(TRANSFERDTONOWALLET)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@Test
	@Sql("/import.sql")
	void testTransferWalletFroMercFailure() {

		webTestClient
				.post()
				.uri("/transfer")
				.bodyValue(TRANSFERDTOFROMMERCH)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@Test
	@Sql("/import.sql")
	void testTransferWalletNoValueFailure() {

		webTestClient
				.post()
				.uri("/transfer")
				.bodyValue(TRANSFERDTONOVALUE)
				.exchange()
				.expectStatus().isBadRequest();

	}

}
