package com.example.crud.crud.controllers;

import com.example.crud.crud.dtos.FiltrosDTO;
import com.example.crud.crud.dtos.ItemPedidoDTO;
import com.example.crud.crud.dtos.PedidoDTO;
import com.example.crud.crud.models.PedidoModel;
import com.example.crud.crud.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/pedidos", produces = {"application/json"})
@Tag(name = "Pedido Controller")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Cria um pedido.", method = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar um pedido"),
    })
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody @Valid PedidoDTO pedidoDTO) throws Exception {
        PedidoDTO pedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
    @Operation(summary = "Aplica desconto em um pedido existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desconto aplicado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao aplicar desconto"),
    })
    @PutMapping("/{id}/{desconto}")
    public ResponseEntity<PedidoDTO> aplicarDescontoPedido(@PathVariable UUID id, @PathVariable Double desconto) throws Exception {
        PedidoDTO pedidoDesconto = pedidoService.aplicarDescontoPedido(id, desconto);
        return ResponseEntity.ok(pedidoDesconto);
    }
    @Operation(summary = "Altera um pedido existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido alterado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao alterar pedido."),
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable UUID id, @RequestBody @Valid PedidoModel pedido) throws Exception {
        PedidoDTO pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }
    @Operation(summary = "Adiciona um item a um pedido existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item inserido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao inserir item"),
    })
    @PutMapping("/adicionar/{id}")
    public ResponseEntity<PedidoDTO> adicionarItensPedido(@PathVariable UUID id, @RequestBody @Valid ItemPedidoDTO itemPedidoDTO) throws Exception {
        PedidoDTO pedidoAtualizado = pedidoService.adicionarItemPedido(id, itemPedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }
    @Operation(summary = "Deletea um pedido existente.", method = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar pedido"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPedidoPorId(@PathVariable UUID id) {
        pedidoService.excluirPedidoPorId(id);
        return ResponseEntity.ok("Pedido deletado com sucesso!");
    }
    @Operation(summary = "Lista os pedidos existentes.", method = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar Pedidos"),
    })
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidosFiltros(@RequestBody @Valid FiltrosDTO filtros) {
        List<PedidoDTO> pedidos = pedidoService.listarPedidosFiltros(filtros);
        return ResponseEntity.ok(pedidos);
    }
}
