package com.example.crud.crud.respositories;

import com.example.crud.crud.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID>, QuerydslPredicateExecutor<PedidoModel> {

}
