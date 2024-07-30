package com.example.crud.crud.respositories;

import com.example.crud.crud.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID>, QuerydslPredicateExecutor<ProdutoModel> {

}
