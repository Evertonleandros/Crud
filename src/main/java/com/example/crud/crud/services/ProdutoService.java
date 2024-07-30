package com.example.crud.crud.services;

import com.example.crud.crud.Utils.FiltrosUtils;
import com.example.crud.crud.dtos.FiltrosDTO;
import com.example.crud.crud.dtos.ProdutoDTO;
import com.example.crud.crud.models.ItemPedidoModel;
import com.example.crud.crud.models.ProdutoModel;
import com.example.crud.crud.respositories.ItemPedidoRepository;
import com.example.crud.crud.respositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setTipo(produtoDTO.getTipo());
        produto.setIsAtivo(produtoDTO.getAtivo());
        produtoRepository.save(produto);
        return produtoDTO;
    }

    @Transactional
    public ProdutoDTO atualizarProduto(UUID id, ProdutoDTO produtoDto) {
        Optional<ProdutoModel> produtoExistente = produtoRepository.findById(id);
        produtoExistente.orElseThrow().setNome(produtoDto.getNome());
        produtoExistente.orElseThrow().setPreco(produtoDto.getPreco());
        produtoExistente.orElseThrow().setTipo(produtoDto.getTipo());
        produtoExistente.orElseThrow().setIsAtivo(produtoDto.getAtivo());
        return new ProdutoDTO(produtoRepository.save(produtoExistente.orElseThrow()));
    }
    @Transactional
    public void excluirProdutoPorId(UUID id) throws Exception {
        Optional<ProdutoModel> produtoExistente = produtoRepository.findById(id);
        List<Optional<ItemPedidoModel>> ItemPedidoModelLista = itemPedidoRepository.findByIdProduto(id);
        if(ItemPedidoModelLista.isEmpty()){
            produtoRepository.deleteById(produtoExistente.orElseThrow().getProdutoId());
        }else{
            throw new Exception("Produto vinculado a um pedido. Não é possivel excluir um produdo vinculado a um pedido.");
        }
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarProdutosFiltros(FiltrosDTO filtros, Pageable pageable) {
        Iterable<ProdutoModel> produtoModelLista = produtoRepository.findAll(FiltrosUtils.verificaFiltrosNulos(filtros), pageable);
        List<ProdutoDTO> produtoDTOLista = new ArrayList<>();
        for(ProdutoModel model : produtoModelLista){
            produtoDTOLista.add(new ProdutoDTO(model));
        }
        return produtoDTOLista;
    }
}
