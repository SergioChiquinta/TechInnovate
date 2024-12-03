package com.techinnovate.techinnovate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techinnovate.techinnovate.controller.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    @Query("Select p from Producto p order by p.nombre")
    List<Producto> buscarTodos();

    @Query("from Producto p where p.distribuidor.id = ?1 order by p.nombre")
    List<Producto> buscarPorDistribuidor(int distribuidorId);
    
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria ORDER BY p.nombre")
    List<Producto> buscarPorCategoria(String categoria);
    
    @Query("SELECT DISTINCT p.categoria FROM Producto p")
    List<String> obtenerCategorias();
    
}
