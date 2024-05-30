package br.dev.rodrigopinheioro.picpay.service;

import br.dev.rodrigopinheioro.picpay.client.AuthorizationClient;
import br.dev.rodrigopinheioro.picpay.controller.dto.TransferDto;
import br.dev.rodrigopinheioro.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    @SuppressWarnings("null")
    public boolean isAuthorized(TransferDto transferDto){
        var resp = authorizationClient.isAuthorized();
        if(resp.getStatusCode().isError()){
            throw new PicPayException();
        }
        return resp.getBody().authorized();
    }
}
