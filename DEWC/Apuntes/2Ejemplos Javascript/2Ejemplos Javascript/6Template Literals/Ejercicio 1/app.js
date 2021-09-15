let nombre = prompt('Cual es tu nombre?');
let edad = prompt('Cuál es tu edad?')

document.getElementById('mensaje').innerHTML = `bienvenido ${nombre} de ${edad} años`;

// Comillas.
nombre = 'Txema';
nombre = 'Y entonces dije, \'buen curso\' ';

console.log(nombre);
// Concatenar un STRING

nombre = 'J' + 'A' + 'V' + 'A' + 'S' + 'C' + 'R' + 'I' + 'P' + 'T';
console.log(nombre);
// Concatenar 2 variables de strings.

let aprendiendo = 'Aprendiendo',
    tecnologia = 'JavaScript';

console.log(`${aprendiendo} ${tecnologia}`);