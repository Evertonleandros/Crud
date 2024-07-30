package com.example.crud.crud.respositories;

import com.example.crud.crud.models.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, UUID> {
    @Query(value = "SELECT * FROM itens_pedido ip WHERE produto_id = :produto_id ", nativeQuery = true)
    public List<Optional<ItemPedidoModel>> findByIdProduto(@Param("produto_id") UUID produto_id);
}
