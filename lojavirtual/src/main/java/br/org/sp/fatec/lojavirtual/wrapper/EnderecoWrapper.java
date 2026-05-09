package br.org.sp.fatec.lojavirtual.wrapper;

import br.org.sp.fatec.lojavirtual.dto.EnderecoResultDto;
import br.org.sp.fatec.lojavirtual.model.Endereco;

public class EnderecoWrapper {

    public static EnderecoResultDto converterEnderecoDto(Endereco endereco) {

        EnderecoResultDto dto = new EnderecoResultDto();

        dto.setId(endereco.getId());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getBairro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());

        if (endereco.getCliente() != null) {
            dto.setClienteId(endereco.getCliente().getId());
        }

        return dto;
    }
}
