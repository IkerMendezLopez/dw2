// Un arreglo más completo y puede tener de todo:
const persona = {
    nombre: 'Txema',
    apellido: 'Serrano',
    profesion: 'Desarrollador Web',
    edad: 50,
    email: 'correo@correo.com',
    musica: ['Trance', 'Rock', 'Grunge'],
    hogar: {
        ciudad: 'Arrasate',
        provincia: 'Gipuzkoa'
    },
    fechaCumple: function() {
        return new Date().getFullYear() - this.edad;
    }
}

let valor;

// ACCEDER A UN VALOR
valor = persona.nombre;
console.log(valor);
// otra forma de acceder al valor
valor = persona['profesion'];
console.log(valor);
// acceder a la edad
valor = persona.edad;
console.log(valor);
// acceder a la musica
valor = persona.musica;
valor = persona.musica[1];
console.log(valor);
// acceder al objeto
valor = persona.hogar.ciudad;
console.log(valor);
// Otra forma aunque menos utilizada
valor = persona['hogar']['provincia'];
console.log(valor);
// acceder a la funcion
valor = persona.fechaCumple();

console.log(valor);


// Arreglo de Objetos
let autos = [
    { modelo: 'Mustang', motor: 6.0 },
    { modelo: 'Camaro', motor: 6.1 },
    { modelo: 'Challenger', motor: 6.1 },
];

for (let i = 0; i < autos.length; i++) {
    console.log(autos[i].modelo);
}