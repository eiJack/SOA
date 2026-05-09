package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.PedidoFormDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoListDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoResultDto;
import br.org.sp.fatec.lojavirtual.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/")
    public ResponseEntity<PedidoResultDto> cadastrar(
            @Valid @RequestBody PedidoFormDto formulario,
            UriComponentsBuilder builder){
        var pedido = pedidoService.cadastrarPedido(formulario);
        var uri = builder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping("/")
    public ResponseEntity<Page<PedidoListDto>> listar(
            Pageable paginacao,
            @RequestParam(required = false, name="cliente_id") Long clienteId
    ){
        var pedidos = pedidoService.buscaPaginada(paginacao, clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResultDto> BuscarPorId(@PathVariable Long id){
        var pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

}
