
package com.techinnovate.techinnovate.controller.service;

import com.techinnovate.techinnovate.controller.domain.Producto;
import org.springframework.stereotype.Service;
/*import java.util.ArrayList;*/
import java.util.List;
import com.techinnovate.techinnovate.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
    public class ProductoService {

        private final ProductoRepository productoRepository;

            public ProductoService(ProductoRepository productoRepository) {
                this.productoRepository = productoRepository;
            }

        public List<Producto> buscarDestacados(){
            
            /*List<Producto> destacados = new ArrayList<>();
            
            Producto articulo = new Producto();
            articulo.setNombre("SOPORTE AUDÍFONO GAMING");
            articulo.setDescripcion("XTECH YUREI XTH690");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/84/55/84552802-2161-4995-8896-4613b0666c84.jpg");
            articulo.setPrecio(139.90);
            destacados.add(articulo);
            
            articulo = new Producto();
            articulo.setNombre("TECLADO GAMING ESPAÑOL");
            articulo.setDescripcion("XTECH CHEVALIER XTK505S");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/dd/5b/dd5b93b8-1f27-4227-869f-0af15db8d87f.jpg");
            articulo.setPrecio(89.90);
            destacados.add(articulo);
            
            articulo = new Producto();
            articulo.setNombre("AUDÍFONO GAMING");
            articulo.setDescripcion("HYPERX CLOUD ALPHA S 7.1 SURROUND SOUND");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/c9/4c/c94c16bd-e307-423c-8cf6-00311ac62763.jpg");
            articulo.setPrecio(399.90);
            destacados.add(articulo);
            
            articulo = new Producto();
            articulo.setNombre("MOUSEPAD GAMING");
            articulo.setDescripcion("LOGITECH G240 CLOTH MEDIO (M)");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/4f/9d/4f9d3aa5-e5d2-4728-a4d6-14817dfa3282.jpg");
            articulo.setPrecio(79.90);
            destacados.add(articulo);

            articulo = new Producto();
            articulo.setNombre("MOUSE GAMING INALÁMBRICO");
            articulo.setDescripcion("HYPERX PULSEFIRE DART");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/72/91/729130a2-0dbf-462e-88d9-7b742d61878a.jpg");
            articulo.setPrecio(429.90);
            destacados.add(articulo);

            articulo = new Producto();
            articulo.setNombre("NINTENDO SWITCH PLAYSTAND");
            articulo.setDescripcion("MODELO ZELDA BY HORI");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/e8/4b/e84b839b-8943-4a3e-9428-6e2447743ce0.jpg");
            articulo.setPrecio(79.90);
            destacados.add(articulo);

            articulo = new Producto();
            articulo.setNombre("MOUSE GAMING");
            articulo.setDescripcion("LOGITECH G300S 2500 DPI");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/ad/b9/adb93add-ef2d-4901-adea-9ae82b805276.jpg");
            articulo.setPrecio(135.90);
            destacados.add(articulo);

            articulo = new Producto();
            articulo.setNombre("TECLADO GAMING");
            articulo.setDescripcion("LOGITECH G PRO - USB CARBON");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/d3/6f/d36f2ac1-1818-4ce5-9d09-c13efdef44f5.jpg");
            articulo.setPrecio(399.90);
            destacados.add(articulo);

            articulo = new Producto();
            articulo.setNombre("MOUSEPAD");
            articulo.setDescripcion("HYPERX FURY S PRO SPEED EDITION LARGO (L)");
            articulo.setImagenUrl("https://content.emarket.pe/common/collections/products/f2/4a/f24aac93-cb0b-489c-9d9f-a1bdfe9f465c.jpg");
            articulo.setPrecio(89.90);
            destacados.add(articulo);
            
            return destacados;*/

            return productoRepository.buscarTodos();
        }
        
        public List<Producto> buscarPorDistribuidor(int distribuidorId) {
            return productoRepository.buscarPorDistribuidor(distribuidorId);
        }
        
        public List<Producto> buscarPorCategoria(String categoria) {
            return productoRepository.buscarPorCategoria(categoria);
        }

        public List<String> obtenerCategorias() {
            return productoRepository.obtenerCategorias();
        }
        
        public Producto buscarPorId(int id) {
            return productoRepository.findById(id).orElse(null);
        }

    }

