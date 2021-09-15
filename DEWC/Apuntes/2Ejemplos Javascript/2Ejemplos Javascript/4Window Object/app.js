// Window Object
// window en la consola


// No es necesario ya que window es el global
window.console.log('hola');
window.alert("alert!");

// Prompt
const nombre = prompt();

// Confirm
if (confirm('Eliminar ?')) {
    console.log('Eliminado')
} else {
    console.log('Nada pasa');
}

// Obtener altura y ancho de la ventana

let altura, ancho;
altura = window.outerHeight;
ancho = window.outerWidth;

// Altura sin la interfaz
altura = window.innerHeight;
ancho = window.innerWidth;


console.log(altura);
console.log(ancho);


// Location
let ubicacion = window.location;
console.log(ubicacion);
console.log(ubicacion.hostname);
console.log(ubicacion.port);

// añadir a la url ?id=20&nombre=juan
console.log(ubicacion.search);
var seguir = prompt('Seguir?');
window.location.href = 'http://google.com';

// History Object

window.history.go(-3);
window.history.length;

// Navigator Object
console.lpdog(window.navigator)
console.log(window.navigator.appName);
console.log(window.navigator.appVersion);
console.log(window.navigator.userAgent)
console.log(window.navigator.language)