// Operador if
const puntaje = '1000';

// es igual
if (puntaje == 1000) {
    console.log("Si es igual!");
} else {
    console.log("No no es igual");
}

// No es igual
if (puntaje != 1000) {
    console.log("Si! es diferente!");
} else {
    console.log("No, no es diferente");
}
// comparador estricto de tipo y valor
if (puntaje === 1000) {
    console.log("Si es igual!");
} else {
    console.log("No no es igual");
}

// comparador estricto de tipo y valor
if (puntaje !== 1000) {
    console.log("Si es DIFERENTE (ESTRICTO) !");
} else {
    console.log("No no es igual");
}

if (puntaje) {
    console.log(`el puntaje es de ${puntaje}`);
} else {
    console.log('No hay puntaje');
}

// Comprobar que la variable existe
if (typeof puntaje != 'undefined') {
    console.log(`el puntaje es de ${puntaje}`);
} else {
    console.log('No hay puntaje');
}

// Algunos signos son < > también >=   <=
let dinero = 500;
let totalCarrito = 300;

if (dinero > totalCarrito) {
    console.log('Pago Correcto');
} else {
    console.log('Fondos Insuficientes');
}

// También puede ser sin llaves

if (dinero > totalCarrito)
    console.log('Pago Correcto');
else
    console.log('Fondos Insuficientes');



// else if
let hora = 20;
if (hora <= 10) {
    console.log('Buenos dias');
} else if (hora <= 18) {
    console.log('buenas tardes');
} else {
    console.log('buenas noches');
}

hora = 25;
// Operador &&
if (hora > 0 && hora <= 12) {
    console.log('Buenos dias');
} else if (hora > 12 && hora <= 18) {
    console.log('Buenas Tardes')
} else if (hora > 18 && hora <= 24) {
    console.log('Buenas Noches');
} else {
    console.log('Hora no valida');
}

// operador || OR
let efectivo = 300;
let credito = 300;
totalCarrito = 700;

if (totalCarrito < efectivo || totalCarrito < credito) {
    console.log('Puedes pagar con efectivo o credito');
} else {
    console.log('No puedes pagar');
}

// operador ||
efectivo = 300;
credito = 400;
let disponible = efectivo + credito;
totalCarrito = 700;

if (efectivo > totalCarrito || credito > totalCarrito) {
    console.log('Puedes pagar con efectivo o credito');
} else if (disponible >= totalCarrito) {
    console.log('Paga con ambos');
} else {
    console.log('No puedes pagar');
}


// Ternary

let logueado = true;

console.log(logueado === true ? 'Si se logueo' : 'No se logueo');