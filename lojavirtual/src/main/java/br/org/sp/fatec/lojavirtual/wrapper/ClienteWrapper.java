package br.org.sp.fatec.lojavirtual.wrapper;

import br.org.sp.fatec.lojavirtual.dto.ClienteFormAtualizacaoDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteFormCadastroDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteListDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteResultDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.util.UtilitarioTexto;

public class ClienteWrapper {

    public static Cliente converterFormCadastro(ClienteFormCadastroDto form) {
        // Cria um cliente
        Cliente cliente = new Cliente();
        //Copia os dados e
        //Limpa os campos
        cliente.setNome(form.getNome());
        cliente.setCpf(UtilitarioTexto.limparCaracteresEspeciais(form.getCpf()));
        cliente.setTelefone(UtilitarioTexto.limparCaracteresEspeciais(form.getTelefone()));
        cliente.setEmail(form.getEmail());
        //devolve o novo cliente
        return cliente;
    }

    public static void atualizarClienteComFormAtualizacao(ClienteFormAtualizacaoDto form, Cliente cliente) {
        cliente.setNome(form.getNome());
        cliente.setTelefone(UtilitarioTexto.limparCaracteresEspeciais(form.getTelefone()));
        cliente.setEmail(form.getEmail());
    }

    public static ClienteListDto converterParaResultadoDeLista(Cliente cliente) {
        // Cria o dto
        ClienteListDto clienteListDto = new ClienteListDto();
        // Copia os dados
        clienteListDto.setId(cliente.getId());
        clienteListDto.setNome(cliente.getNome());
        clienteListDto.setCpf(cliente.getCpf());
        // Retorna o dto
        return clienteListDto;
    }

    public static ClienteResultDto converterParaResultadoUnico(Cliente cliente) {
        //Cria o dto
        ClienteResultDto clienteResultDto = new ClienteResultDto();
        // Copia os dados
        clienteResultDto.setId(cliente.getId());
        clienteResultDto.setNome(cliente.getNome());
        clienteResultDto.setCpf(cliente.getCpf());
        clienteResultDto.setTelefone(cliente.getTelefone());
        clienteResultDto.setEmail(cliente.getEmail());
        // Retorna o dto
        return clienteResultDto;
    }



}
