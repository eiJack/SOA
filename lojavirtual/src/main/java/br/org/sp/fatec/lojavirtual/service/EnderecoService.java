package br.org.sp.fatec.lojavirtual.service;

import br.org.sp.fatec.lojavirtual.dto.EnderecoFormDto;
import br.org.sp.fatec.lojavirtual.dto.EnderecoResultDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.model.Endereco;
import br.org.sp.fatec.lojavirtual.repository.ClienteRepository;
import br.org.sp.fatec.lojavirtual.repository.EnderecoRepository;
import br.org.sp.fatec.lojavirtual.wrapper.EnderecoWrapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;


    public EnderecoService(EnderecoRepository enderecoRepository,
                           ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    private Endereco falhaEnderecoNaoEncontrado(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Endereço não encontrado"
                ));
    }


    public EnderecoResultDto salvarEndereco (EnderecoFormDto dto){
        // procura se existe o cliente para dar certeza que da para conectar o endereço com o cliente (deve existir cliente para registrar o endereço)
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado"
                ));

        // registra os dados do endereço
        Endereco enderecoNovo = new Endereco();
        enderecoNovo.setLogradouro(dto.getLogradouro());
        enderecoNovo.setBairro(dto.getBairro());
        enderecoNovo.setNumero(dto.getNumero());
        enderecoNovo.setCep(dto.getCep());
        enderecoNovo.setCidade(dto.getCidade());
        enderecoNovo.setEstado(dto.getEstado());
        enderecoNovo.setCliente(cliente);

        // salva
        enderecoNovo = enderecoRepository.save(enderecoNovo);

        //retorno do endereço registrado
        return EnderecoWrapper.converterEnderecoDto(enderecoNovo);
    }

    public EnderecoResultDto buscarEnderecoPorId(Long id) {
        Endereco endereco = falhaEnderecoNaoEncontrado(id);

        return EnderecoWrapper.converterEnderecoDto(endereco);
    }

    public Page<EnderecoResultDto> listarEnderecoPaginado(Pageable paginacao) {
        return enderecoRepository.findAll(paginacao)
                .map(EnderecoWrapper::converterEnderecoDto);
    }

    public void deletarEndereco(Long id) {
        Endereco endereco = falhaEnderecoNaoEncontrado(id);

        enderecoRepository.delete(endereco);
    }

    public EnderecoResultDto atualizarEndereco(Long id, EnderecoFormDto dto) {

        Endereco endereco = falhaEnderecoNaoEncontrado(id);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado"
                ));

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCliente(cliente);

        endereco = enderecoRepository.save(endereco);

        return EnderecoWrapper.converterEnderecoDto(endereco);
    }
}
