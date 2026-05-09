package br.org.sp.fatec.lojavirtual.exceptions;

import java.util.NoSuchElementException;

public class PedidoNaoEncontradoException extends NoSuchElementException {
    public PedidoNaoEncontradoException() {
        super("Pedido nao encontrado");
    }
}
