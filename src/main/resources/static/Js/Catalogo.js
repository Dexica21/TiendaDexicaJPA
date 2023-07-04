
//creamos variables con los id que usaremos 
const ventanaFlotante = document.getElementById('VentanaFlotante');
const tablaCarrito = ventanaFlotante.querySelector('.table');
const itemcarritocompras = document.querySelector('.CarritoCompras');
const carritoContenido = tablaCarrito.querySelector('tbody');


function Comprar(){
    alert('¡Producto agregado Exitosamente\nRevise el carrito de compras')
}


function agregarproductoclick(event) {

    //capturamos el evento al hacer click en un card del producto
    const boton = event.target;
    const item = boton.closest('.card');

    //capturamos el titulo, precio y imagen
    const Titulo = item.querySelector('.card-title').textContent;
    const Precio = item.querySelector('.card-Precio').textContent;
    const Imagen = item.querySelector('.img-thumbnail').src;

    //y lo guardamos en una función que crearemos mas abajo 
    AgregarItemsAlCarrito(Titulo, Precio, Imagen);
}


function AgregarItemsAlCarrito(Titulo, Precio, Imagen) {

    //Aca recopilamos los productos que ya estan en el carrito para que no se repita 
    const elemento= tablaCarrito.getElementsByClassName('shoppingCardItemTitle') 
    for(let i = 0; i < elemento.length; i++){
        if(elemento[i].innerText === Titulo){
            CantidadRepetida.value++;
            $('.toast').toast('show');
            ActualizarTotal()
            return;
        }
    }

    //creamos un nuevo elemento tr y remplazamos las variables anteriores que es la imagen, titulo y precio
    const fila = document.createElement('tr');
    const contenidoFila = `
    <table class="table table-bordered">
        <tr>
            <td class="tde"><img src=${Imagen} alt=""></td>
            <td class="shoppingCardItemTitle"><p>${Titulo}</p></td>
            <td class="itemPrecio">${Precio}</td>
            <td><input class="inputcantidad" type="number" min="1" value="1" required></td>
            <td><button class="btn btn-danger buttondelete">X</button></td>
        </tr>
    </table>`
        ;
    fila.innerHTML = contenidoFila;
    carritoContenido.appendChild(fila);


    fila.querySelector('.buttondelete').addEventListener('click', QuitarProductoTabla)

    fila.querySelector('.inputcantidad').addEventListener('change', ActualizarCantidad);

    ActualizarTotal()
}


//Este modulo es para que al precionar al icono de carrito se abra y cuando presionemos de nuevo se cierre 
var ventanaFlotanteVisible = false;

function toggleVentanaFlotante() {
    if (ventanaFlotanteVisible) {
        ventanaFlotante.style.display = "none";
        ventanaFlotanteVisible = false;
    } else {
        ventanaFlotante.style.display = "block";
        ventanaFlotanteVisible = true;
    }
}



//seleccionaremos todos los elementos con el .add-cart 
const addToShopping = document.querySelectorAll('.add-cart')

//cuando hacemos click a uno de los productos se ejecutara la funcion para agregar productos al carrito
addToShopping.forEach(agregarboton => {
    agregarboton.addEventListener('click', agregarproductoclick)
})
    itemcarritocompras.addEventListener('click', toggleVentanaFlotante);





//hacemos un recorrido por los productos en el carrito capturando el item del precio y de la cantidad y calculamos el total 
function ActualizarTotal(){
    let total = 0;
    const TotalCarrito = document.querySelector('.preciocar')
    const totalCarritoitems = document.querySelectorAll('tr')
    
    totalCarritoitems.forEach(totalCarritoitems => {
        const preciotabla = totalCarritoitems.querySelector('.itemPrecio');
        const precioLimpio= Number(preciotabla.textContent.replace('S/.',''));


        const inputCantidad= totalCarritoitems.querySelector('.inputcantidad')     
        const valorcantidad = Number(inputCantidad.value);
        total=total+ precioLimpio *  valorcantidad;    
    })
    TotalCarrito.innerHTML = `Total: S/.${total.toFixed(2)}`
}



//capturamos el evento para capturar el click que hacemos al boton y lo removemos, luego utilizamos la funcion actualizartotal 
function QuitarProductoTabla(event){
    const clickearBoton = event.target;
    clickearBoton.closest('tr').remove();
    ActualizarTotal();
}

//Actualizamos la cantidad que cuando se modifique cambie el precio 
function ActualizarCantidad(event){
    const input = event.target;
    if(input.value <= 0){
        input.value=1
    }
    ActualizarTotal()
    
}



//Filtrado checkbox Catalogo
function filtrarProductos() {
    var modelosSeleccionados = document.querySelectorAll('input[type="checkbox"][id^="modelo"]:checked');
    var coloresSeleccionados = document.querySelectorAll('input[type="checkbox"][id^="color"]:checked');

    var productos = document.getElementsByClassName('card-item');
    for (var i = 0; i < productos.length; i++) {
        var modelo = productos[i].getAttribute('data-modelo');
        var color = productos[i].getAttribute('data-color');

        var modeloFiltrado = modelosSeleccionados.length === 0 || Array.from(modelosSeleccionados).some(x => x.value === modelo);
        var colorFiltrado = coloresSeleccionados.length === 0 || Array.from(coloresSeleccionados).some(x => x.value === color);

        productos[i].style.display = modeloFiltrado && colorFiltrado ? 'block' : 'none'; //se utiliza un terneario para indicar que se muestre o no
    }
}


//Filtrado Barra de Busqueda
//capturamos palabra por palabra lo que escribimos
document.addEventListener('keyup', e => {
    // Buscamos con la letra que ingresamos en el input que tiene el id buscador, y se captura letra por letra
    if (e.target.matches('#buscador')) {
        const searchTerm = e.target.value.toLowerCase();

    //realizamos un recorrido por todos los cards de los productos capturando los titulos y si el titulo coincide se muestra o no
    document.querySelectorAll('.card').forEach(Cards => {

        const cardTitle = Cards.querySelector('.card-title').textContent.toLowerCase();

        if (cardTitle.includes(searchTerm)) {
          Cards.style.display = 'block'; // Mostrar el elemento
        } else {
          Cards.style.display = 'none'; // Ocultar el elemento
        }
        });
    }
});


