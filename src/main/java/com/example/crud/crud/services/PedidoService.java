package com.example.crud.crud.services;

import com.example.crud.crud.Utils.FiltrosUtils;
import com.example.crud.crud.dtos.FiltrosDTO;
import com.example.crud.crud.dtos.ItemPedidoDTO;
import com.example.crud.crud.dtos.PedidoDTO;
import com.example.crud.crud.enuns.EnumSituacaoPedido;
import com.example.crud.crud.enuns.EnumTipoItem;
import com.example.crud.crud.models.ItemPedidoModel;
import com.example.crud.crud.models.PedidoModel;
import com.example.crud.crud.models.ProdutoModel;
import com.example.crud.crud.respositories.ItemPedidoRepository;
import com.example.crud.crud.respositories.PedidoRepository;
import com.example.crud.crud.respositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) throws Exception {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setSituacao(pedidoDTO.getSituacao());
        List<ItemPedidoModel> listItensProdutos = new ArrayList<>();
        double valorTotal = 0;
        for(ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItens()){
            Optional<ProdutoModel> produtoModel = produtoRepository.findById(UUID.fromString(itemPedidoDTO.getProdutoId()));
            if(produtoModel.orElseThrow().getIsAtivo()) {
                ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
                itemPedidoModel.setProduto(produtoModel.orElseThrow());
                itemPedidoModel.setPedido(pedidoModel);
                itemPedidoModel.setQuantidade(itemPedidoDTO.getQuantidade());
                listItensProdutos.add(itemPedidoModel);
                valorTotal += produtoModel.orElseThrow().getPreco() * itemPedidoModel.getQuantidade();
            }else {
                throw new Exception("Produto desativado! Não é possivel adicionar um produto desativado em um pedido.");
            }
        }
        pedidoModel.setValorTotal(valorTotal);
        pedidoModel.setItens(listItensProdutos);

        pedidoRepository.save(pedidoModel);

        return new PedidoDTO(pedidoModel);
    }

    @Transactional
    public PedidoDTO adicionarItemPedido(UUID id, ItemPedidoDTO itemPedidoDTO) throws Exception {
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(id);
        List<ItemPedidoModel> itemPedidoModelLista = pedidoExistente.orElseThrow().getItens();
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(UUID.fromString(itemPedidoDTO.getProdutoId()));
        if(produtoModel.orElseThrow().getIsAtivo()){
            ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
            itemPedidoModel.setProduto(produtoModel.orElseThrow());
            itemPedidoModel.setPedido(pedidoExistente.orElseThrow());
            itemPedidoModel.setQuantidade(itemPedidoDTO.getQuantidade());
            itemPedidoRepository.save(itemPedidoModel);
            itemPedidoModelLista.add(itemPedidoModel);
        }else {
            throw new Exception("Produto desativado! Não é possivel adicionar um produto desativado em um pedido.");
        }
        pedidoExistente.orElseThrow().setItens(itemPedidoModelLista);
        return new PedidoDTO(pedidoExistente.orElseThrow());
    }
    @Transactional
    public PedidoDTO atualizarPedido(UUID id, PedidoModel pedido) {
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(id);
        pedidoExistente.orElseThrow().setPercentualDesconto(pedido.getPercentualDesconto());
        pedidoExistente.orElseThrow().setSituacao(pedido.getSituacao());
        List<ItemPedidoModel> itemPedidoModelLista = new ArrayList<>();
        pedidoExistente.orElseThrow().setItens(itemPedidoModelLista);
        return new PedidoDTO(pedidoRepository.save(pedidoExistente.orElseThrow()));
    }
    @Transactional
    public void excluirPedidoPorId(UUID id) {
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(id);
        pedidoRepository.deleteById(pedidoExistente.orElseThrow().getPedidoId());
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarPedidosFiltros(FiltrosDTO filtros) {
        Iterable<PedidoModel> pedidoModelLista = pedidoRepository.findAll(FiltrosUtils.verificaFiltrosNulos(filtros));
        List<PedidoDTO> pedidoDTOLista = new ArrayList<>();
        for(PedidoModel model : pedidoModelLista){
            pedidoDTOLista.add(new PedidoDTO(model));
        }
        return pedidoDTOLista;
    }

    public PedidoDTO aplicarDescontoPedido(UUID pedidoId, Double percentualDesconto) throws Exception {
        Optional<PedidoModel> pedidoModel = pedidoRepository.findById(pedidoId);
        Double valorTotalDesc = 0.0;
        for(ItemPedidoModel itemPedidoModel : pedidoModel.orElseThrow().getItens()){
            if (itemPedidoModel.getProduto().getTipo().equals(EnumTipoItem.Produto.toString())
                    && itemPedidoModel.getPedido().getSituacao().equals(EnumSituacaoPedido.Aberto.toString())) {
                valorTotalDesc += itemPedidoModel.getProduto().getPreco() * (percentualDesconto/100);
            }else {
                throw new Exception("Pedido fechado! Não é possível aplicar desconto em pedidos fechados.");
            }
        }
        pedidoModel.orElseThrow().setPercentualDesconto(percentualDesconto);
        pedidoModel.orElseThrow().setValorTotal(pedidoModel.orElseThrow().getValorTotal() - valorTotalDesc);
        pedidoRepository.save(pedidoModel.orElseThrow());
        return new PedidoDTO(pedidoModel.orElseThrow());
    }
}
