package br.org.sp.fatec.lojavirtual.exceptions;

import java.util.NoSuchElementException;

public class ClienteNaoEncontradoException extends NoSuchElementException {

    public ClienteNaoEncontradoException() {
        super("Cliente não cadastrado");
    }
}
