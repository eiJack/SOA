package br.org.sp.fatec.lojavirtual.exceptions;

import java.util.NoSuchElementException;

public class ProdutoNaoEncontradoException extends NoSuchElementException {

    public ProdutoNaoEncontradoException() {
        super("Produto não cadastrado");
    }
}
