package br.dev.rodrigopinheioro.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBallanceException extends PicPayException {
    private final BigDecimal value;

    public InsufficientBallanceException(BigDecimal value) {
        this.value = value;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient balance");
        pb.setDetail("You cannot transfer a value ("+value+") bigger than your current balance.");
        return pb;
    }
}
