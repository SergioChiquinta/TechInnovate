// Función para abrir Modal de detalles de producto
function abrirModal(productoId) {
    fetch('/api/producto/' + productoId)
        .then(response => response.json())
        .then(data => {
            document.getElementById('productoNombre').textContent = data.nombre;
            document.getElementById('productoImagen').src = data.imagenUrl || 'default-image.jpg';
            document.getElementById('productoDetalle').textContent = data.detalle || 'Sin detalle disponible';
            document.getElementById('productoMarca').textContent = data.marca || 'Desconocida';
            document.getElementById('productoColor').textContent = data.color || 'Desconocido';
            document.getElementById('productoGarantia').textContent = data.garantia ? data.garantia + ' meses' : 'Sin garantía';
            document.getElementById('productoPrecio').textContent = data.precio ? 'S/.' + data.precio.toFixed(2) : 'S/.0.00';
            document.getElementById('productoStock').textContent = data.stock || '0';

            var productoModal = new bootstrap.Modal(document.getElementById('productoModal'));
            productoModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al cargar los detalles del producto.');
        });
}

// Funciones para el Carrito
let carrito = [];
let totalProductos = 0;
let totalPrecio = 0;

function agregarAlCarrito(productoId, stock) {
    const productoExistente = carrito.find(item => item.id === productoId);
    if (productoExistente) {
        if (productoExistente.cantidad < stock) {
            productoExistente.cantidad++;
            totalProductos++;
            totalPrecio += parseFloat(productoExistente.precio);
            actualizarCarrito();
            mostrarNotificacion("Cantidad incrementada en el carrito.");
        } else {
            mostrarNotificacion("No puedes agregar más de este producto. Stock máximo alcanzado.");
        }
        return;
    }

    fetch('/api/producto/' + productoId)
        .then(response => response.json())
        .then(data => {
            if (stock > 0) {
                const producto = {
                    id: productoId,
                    nombre: data.nombre,
                    imagen: data.imagenUrl,
                    descripcion: data.descripcion || 'Sin descripción disponible',
                    precio: parseFloat(data.precio || 0).toFixed(2),
                    cantidad: 1,
                    stock: stock
                };

                carrito.push(producto);
                totalProductos++;
                totalPrecio += parseFloat(producto.precio);

                actualizarCarrito();
                mostrarNotificacion("Producto agregado al carrito con éxito!");
            } else {
                mostrarNotificacion("Este producto está agotado.");
            }
        })
        .catch(error => console.error('Error al agregar producto al carrito:', error));
}

function incrementarCantidad(productoId) {
    const producto = carrito.find(item => item.id === productoId);
    if (producto && producto.cantidad < producto.stock) {
        producto.cantidad++;
        totalProductos++;
        totalPrecio += parseFloat(producto.precio);
        actualizarCarrito();
    } else {
        mostrarNotificacion("No puedes agregar más de este producto. Stock máximo alcanzado.");
    }
}

function decrementarCantidad(productoId) {
    const producto = carrito.find(item => item.id === productoId);
    if (producto && producto.cantidad > 1) {
        producto.cantidad--;
        totalProductos--;
        totalPrecio -= parseFloat(producto.precio);
        actualizarCarrito();
    } else {
        mostrarNotificacion("No puedes tener menos de 1 cantidad.");
    }
}

function actualizarCarrito() {
    const carritoVacio = document.getElementById('carritoVacio');
    const carritoProductos = document.getElementById('carritoProductos');
    const listaCarrito = document.getElementById('listaCarrito');
    const totalProductosEl = document.getElementById('totalProductos');
    const totalPrecioEl = document.getElementById('totalPrecio');
    const btnFinalizarCompra = document.getElementById('btnFinalizarCompra');

    listaCarrito.innerHTML = '';

    if (carrito.length > 0) {
        carritoVacio.classList.add('d-none');
        carritoProductos.classList.remove('d-none');

        carrito.forEach(producto => {
            const li = document.createElement('li');
            li.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');
            li.innerHTML = `
                <div>
                    <img src="${producto.imagen}" alt="${producto.nombre}" style="width: 50px; margin: 10px;">
                    <span style="margin-right: 20px;">${producto.nombre}</span>
                    <small class="text-muted" style="margin-right: 20px;">Precio: S/. ${producto.precio}</small>
                    <button class="btn btn-sm btn-outline-secondary" onclick="decrementarCantidad(${producto.id})">-</button>
                    <span class="mx-2">${producto.cantidad}</span>
                    <button class="btn btn-sm btn-outline-secondary" onclick="incrementarCantidad(${producto.id})">+</button>
                </div>
                <span class="badge bg-primary rounded-pill" style="margin-right: 10px;">Stock: ${producto.stock}</span>
            `;

            listaCarrito.appendChild(li);
        });

        totalProductosEl.textContent = totalProductos;
        totalPrecioEl.textContent = totalPrecio.toFixed(2);

        btnFinalizarCompra.disabled = false;
    } else {
        carritoVacio.classList.remove('d-none');
        carritoProductos.classList.add('d-none');

        btnFinalizarCompra.disabled = true;
    }
}

function mostrarNotificacion(mensaje) {
    Toastify({
        text: mensaje,
        duration: 3000,
        gravity: "top",
        position: "left",
        backgroundColor: "#3371ff"
    }).showToast();
}

// Función para abrir la boleta electrónica
function finalizarCompra() {
    if (carrito.length > 0) {
        let ventanaBoleta = window.open('', '_blank');
        
        let contenido = `
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Boleta Electrónica</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    body { font-family: Arial, sans-serif; }
                    table { width: 100%; margin-top: 20px; }
                    th, td { padding: 8px 12px; text-align: left; }
                    th { background-color: #f8f9fa; }
                    .total { font-size: 1.2em; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class="container mt-5">
                    <h1 class="text-center mb-4">Boleta Electrónica</h1>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Cantidad</th>
                                <th>Precio Unitario</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
        `;

        carrito.forEach(producto => {
            let totalProducto = (parseFloat(producto.precio) * producto.cantidad).toFixed(2);
            contenido += `
                <tr>
                    <td>${producto.nombre}</td>
                    <td>${producto.cantidad}</td>
                    <td>S/. ${producto.precio}</td>
                    <td>S/. ${totalProducto}</td>
                </tr>
            `;
        });

        contenido += `
            </tbody>
        </table>
        <div class="d-flex justify-content-between">
            <h4 class="total">Total: S/. ${totalPrecio.toFixed(2)}</h4>
            <button class="btn btn-primary" onclick="window.print()">Imprimir Boleta</button>
        </div>
        </div>
        </body>
        </html>
        `;

        ventanaBoleta.document.write(contenido);
        ventanaBoleta.document.close();

        // Resetear el carrito después de mostrar la boleta
        carrito = [];
        totalProductos = 0;
        totalPrecio = 0;
        actualizarCarrito(); // Actualizar la vista del carrito (vacío)
    } else {
        mostrarNotificacion("Tu carrito está vacío. No puedes finalizar la compra.");
    }
}

document.getElementById('btnFinalizarCompra').addEventListener('click', finalizarCompra);
