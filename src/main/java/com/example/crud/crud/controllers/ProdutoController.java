package com.example.crud.crud.controllers;

import com.example.crud.crud.dtos.FiltrosDTO;
import com.example.crud.crud.dtos.ProdutoDTO;
import com.example.crud.crud.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/produtos", produces = {"application/json"})
@Tag(name = "Produto Controller")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Cria um Produto.", method = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar um produto"),
    })
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody @Valid ProdutoDTO produtoDto){
        ProdutoDTO itemVenda = produtoService.criarProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemVenda);
    }

    @Operation(summary = "Atualiza um produdo existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar produto"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoDTO produtoDto) {
        ProdutoDTO produtoAtualizado = produtoService.atualizarProduto(id, produtoDto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @Operation(summary = "Deleta um produdo existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar produto"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProdutoPorId(@PathVariable UUID id) throws Exception {
        produtoService.excluirProdutoPorId(id);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }
    @Operation(summary = "Liata os produdos existente.", method = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar produtos"),
    })
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutosFiltros(@RequestBody @Valid FiltrosDTO filtros, Pageable pageable) {
        List<ProdutoDTO> produtosDTO = produtoService.listarProdutosFiltros(filtros, pageable);
        return ResponseEntity.ok(produtosDTO);
    }

}
