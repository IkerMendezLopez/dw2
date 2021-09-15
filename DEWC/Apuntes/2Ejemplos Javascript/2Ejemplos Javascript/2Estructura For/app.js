// Loops, corren hasta que una condicion se cumple

// For loop
for (let i = 0; i <= 10; i++) {
    console.log(`Numero:  ${i} `);
}

// Interceptar el 2
for (let i = 0; i <= 10; i++) {
    if (i == 2) {
        console.log('siii!!! 2!!!!');
        // con o sin continue;
        continue;
    }
    console.log(`Numero:  ${i} `);
}

// Romper el ciclo en 2
for (let i = 0; i <= 10; i++) {
    if (i == 2) {
        console.log('siii!!! 2!!!!');
        break;
    }
    console.log(`Numero:  ${i} `);
}

// Numero par o impar
for (let i = 0; i <= 10; i++) {
    if (i % 2 == 0) {
        console.log(`Numero ${i} ES PAR `);
    } else {
        console.log(`Numero ${i} ES IMPAR `);
    }
}