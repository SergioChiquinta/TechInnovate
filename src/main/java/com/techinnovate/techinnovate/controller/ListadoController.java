package com.techinnovate.techinnovate.controller;

import com.techinnovate.techinnovate.controller.domain.Producto;
import com.techinnovate.techinnovate.controller.domain.ProductoCompra;
import com.techinnovate.techinnovate.controller.service.ProductoService;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ListadoController {

    private final ProductoService productoService;

    public ListadoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @RequestMapping("/productos.html")
    public String listaProductos(Model model) {
        List<Producto> destacados = productoService.buscarDestacados();
        List<String> categorias = productoService.obtenerCategorias();
        model.addAttribute("productos", destacados);
        model.addAttribute("categorias", categorias);
        return "productos";
    }

    @RequestMapping("/productosPorDistribuidor")
    public String listarProductosPorDistribuidor(@RequestParam("distribuidorId") int distribuidorId, Model model) {
        List<Producto> productos = productoService.buscarPorDistribuidor(distribuidorId);
        List<String> categorias = productoService.obtenerCategorias();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "productos";
    }

    @RequestMapping("/productosPorCategoria")
    public String listarProductosPorCategoria(@RequestParam(value = "categoria", required = false) String categoria, Model model) {
        List<Producto> productos;
        if (categoria == null || categoria.isEmpty()) {
            productos = productoService.buscarDestacados();  // O todos los productos si no hay filtro
        } else {
            productos = productoService.buscarPorCategoria(categoria);
        }
        List<String> categorias = productoService.obtenerCategorias();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSeleccionada", categoria != null ? categoria : ""); // Manejar "Todas las categorías"
        return "productos";
    }

    @RequestMapping("/")
    public String inicio() {
        return "index";
    }

    @RequestMapping("/contacto.html")
    public String contacto() {
        return "contacto";
    }

    @RequestMapping("/nosotros.html")
    public String nosotros() {
        return "nosotros";
    }

    @GetMapping("/producto")
    public ResponseEntity<Producto> obtenerProducto(@RequestParam("id") int id) {
        Producto producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(producto);
    }
    
    @GetMapping("/api/producto/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = productoService.buscarPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}


