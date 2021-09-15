const producto1 = 'Pizza';
const precio1 = 30;
const producto2 = 'Hamburguesa';
const precio2 = 40;

/* let html;
// Método viejo
html = '<ul>' +
    '<li>Orden: ' + producto1 + '</li>' +
    '<li>Precio: $ ' + precio1 + '</li>' +
    '<li>Orden: ' + producto2 + '</li>' +
    '<li>Precio: $ ' + precio2 + '</li>' +
    '<li>Total: $' + (precio1 + precio2) + '</li>';
'</ul>'; */

// Con template Strings.

html = `
    <ul>
        <li>Orden: ${producto1}</li>
        <li>Precio: ${precio1}</li>
        <li>Orden: ${producto2}</li>
        <li>Precio: ${precio2}  </li>
        <li>Total: ${total(precio1, precio2)}</li>
    </ul>
`;

function total(param1, param2) {
    return param1 + param2;
}


// document.getElementById('mensaje').innerHTML = html;

let app = document.querySelector('#mensaje');
app.innerHTML = html;