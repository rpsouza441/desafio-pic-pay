package br.dev.rodrigopinheioro.picpay;

import br.dev.rodrigopinheioro.picpay.controller.dto.WalletDTO;
import br.dev.rodrigopinheioro.picpay.entity.WalletType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @Sql("/remove.sql")
class PicpayApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

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

}
