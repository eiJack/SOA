package br.org.sp.fatec.lojavirtual.service;

import br.org.sp.fatec.lojavirtual.dto.ClienteFormAtualizacaoDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteFormCadastroDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteListDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteResultDto;
import br.org.sp.fatec.lojavirtual.exceptions.ClienteNaoEncontradoException;
import br.org.sp.fatec.lojavirtual.repository.ClienteRepository;
import br.org.sp.fatec.lojavirtual.wrapper.ClienteWrapper;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<ClienteListDto> listarPaginado(Pageable paginacao) {
        var paginaCliente = clienteRepository.findAll(paginacao);
        var paginaClienteConvertido = paginaCliente.map((cliente) -> {
            var dto = ClienteWrapper.converterParaResultadoDeLista( cliente );
            return dto;
        });
        return paginaClienteConvertido;
    }

    public ClienteResultDto buscaPorId(Long id) {
        // Verificar se o cliente existe
        var cliente = clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
        // Converter o cliente
        var dto = ClienteWrapper.converterParaResultadoUnico( cliente );
        // Retornar o dto
        return dto;
    }

    public ClienteResultDto cadastrar(ClienteFormCadastroDto form) {
        // Converter para um novo cliente
        var cliente = ClienteWrapper.converterFormCadastro(form);

        // Verificar se o Cpf já está cadastrado
        if (clienteRepository.findOneByCpf(cliente.getCpf()).isPresent()) {
            throw new ValidationException("CPF já cadastrado");
        }
        // Salvar no banco de dados
        cliente = clienteRepository.save(cliente);

        //Converter para dto
        var dto = ClienteWrapper.converterParaResultadoUnico( cliente );

        // retornar o novo cliente
        return dto;

    }

    public ClienteResultDto atualizar(Long id, @Valid ClienteFormAtualizacaoDto form) {
        // Verificar se o cliente existe
        var cliente = clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
        // Copiar novos dados
        ClienteWrapper.atualizarClienteComFormAtualizacao(form, cliente);
        // Salvar no banco de dados
        cliente = clienteRepository.save(cliente);
        // Converter para dto
        var dto = ClienteWrapper.converterParaResultadoUnico( cliente );
        // Retornar o dto
        return dto;
    }

    public void remover(Long id) {
        // Verificar se o cliente existe
        var cliente =  clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
        // Apagar o cliente
        clienteRepository.delete(cliente);
    }
}
