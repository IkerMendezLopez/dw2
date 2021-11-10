//ARRAYS
const arrPro = [
    ["Araba", "Bizkaia", "Gipuzkoa"],
    ["Navarra"],
    ["Lapurdi", "Behe Nafarroa", "Zuberoa"]
];
const arrMun = [
    ["Gasteiz", "Laguardia", "Salvatierra"],
    ["Bilbo", "Barakaldo", "Durango"],
    ["Donosti", "Arrasate", "Bergara"],
    ["Iruña", "Lizarra", "Ujue"],
    ["Baiona", "Biarritz", "Hendaya"],
    ["Bidarray", "St-Palais"],
    ["Maule", "Etxarri"]

];
var errores = ["DNI", "nombre", "apellido", "direccion", "territorio", "provincia", "municipio", "fecha", "mail", "telefono"];

//EVENTOS
document.getElementById('dni').addEventListener('blur', function () {
    if (!validarDni(document.getElementById('dni').value)) {
        alert("Dni incorrecto");
        errores[1] = "DNI";
    } else {
        errores[0] = "OK";
    }
});
document.getElementById('firstname').addEventListener('blur', function () {
    let nombre = document.getElementById('firstname').value;
    if (moreThan50(nombre)) {
        alert("Solo se permiten 50 caracteres");
    } else {
        let error = false;
        for (let index = 0; index < nombre.length; index++) {
            if (!isLetter(nombre.charAt(index))) {
                alert("Solo se permiten letras");
                error = true;
                errores[1] = "nombre";
                break;
            }
        }
        if (!error) {
            errores[1] = "OK";
        }
    }
});

document.getElementById('lastname').addEventListener('blur', function () {
    let apellido = document.getElementById('lastname').value;
    if (moreThan50(apellido)) {
        alert("Solo se permiten 50 caracteres");
    } else {
        let error = false;
        for (let index = 0; index < apellido.length; index++) {
            if (!isLetter(apellido.charAt(index))) {
                alert("Solo se permiten letras");
                error = true;
                errores[2] = "apellido";
                break;
            }
        }
        if (!error) {
            errores[2] = "OK";
        }
    }
});

document.getElementById('address').addEventListener('blur', function () {
    let dir = document.getElementById('address').value;
    if (moreThan50(dir)) {
        alert("Solo se permiten 50 caracteres");
    } else {
        let error = false;
        for (let index = 0; index < dir.length; index++) {
            if (!isLetter(dir.charAt(index)) && isNaN(dir.charAt(index))) {
                alert("Solo se permiten letras y numeros");
                error = true;
                errores[3] = "direccion";
                break;
            }
        }
        if (!error) {
            errores[3] = "OK";
        }
    }
});

document.getElementById('territorio').addEventListener('blur', function () {
    if (document.getElementById('territorio').value == 0) {
        alert("Selecciona un territorio valido");
        errores[4] = "territorio";
        errores[5] = "provincia";
        errores[6] = "municipio";
        let pro = document.getElementById('provincia');
        pro.innerHTML = '';
        let mun = document.getElementById('municipio');
        mun.innerHTML = '';
    } else {
        errores[4] = "OK";
        cargarProvincia(document.getElementById('territorio').value);
    }
});

document.getElementById('provincia').addEventListener('blur', function () {
    if (document.getElementById('provincia').value == 0) {
        alert("Selecciona una provincia valida");
        errores[5] = "provincia";
        errores[6] = "municipio";
        let mun = document.getElementById('municipio');
        mun.innerHTML = '';
    } else {
        errores[5] = "OK";
        cargarMunicipio(document.getElementById('provincia').value);
    }
});

document.getElementById('municipio').addEventListener('blur', function () {
    if (document.getElementById('municipio').value == 0) {
        alert("Selecciona un municipio valido");
        errores[6] = "municipio";
    } else {
        errores[6] = "OK";
    }
});

document.getElementById('birthday').addEventListener('blur', function () {
    if (!validarFecha(document.getElementById('birthday').value)) {
        alert("Fecha Incorrecta");
        errores[7] = "fecha";
    } else {
        errores[7] = "OK";
    }
});

document.getElementById('email').addEventListener('blur', function () {
    if (!validarMail(document.getElementById('email').value)) {
        alert("Mail Incorrecto");
        errores[8] = "mail";
    } else {
        errores[8] = "OK";
    }
});

document.getElementById('phone').addEventListener('blur', function () {
    if (!validarTel(document.getElementById('phone').value)) {
        alert("Telefono Incorrecto");
        errores[9] = "telefono";
    } else {
        errores[9] = "OK";
    }
});


document.getElementById('politicas').addEventListener('click', function () {
    let btnEnviar = document.getElementById('enviar');
    if (document.getElementById('politicas').checked) {
        btnEnviar.removeAttribute("disabled");
        btnEnviar.addEventListener('click', validar);
    } else {
        btnEnviar.setAttribute("disabled", "disabled");
    }
});

//FUNCIONES
function validar() {
    let error = false;
    for (let index = 0; index < errores.length; index++) {
        if (errores[index] != 'OK') {
            document.getElementById('final_error').style.display = "block";
            error = true;
            break;
        }
    }
    if (!error) {
        document.getElementById('final_error').style.display = "none";
        //var errores = ["DNI", "nombre", "apellido", "direccion", "territorio", "provincia", "municipio", "fecha", "mail", "telefono"];

        let arr = {
            "dni": document.getElementById('dni').value,
            "nombre": document.getElementById('firstname').value,
            "apellido": document.getElementById('lastname').value,
            "direccion": document.getElementById('address').value,
            "territorio": document.getElementById('territorio').value,
            "provincia": document.getElementById('provincia').value,
            "municipio": document.getElementById('municipio').value,
            "edad": calcularEdad(),
            "mail": document.getElementById('email').value,
            "telefono": document.getElementById('phone').value,
        };
        localStorage.setItem(document.getElementById('dni').value, JSON.stringify(arr));
        window.open("thankyou.html", "TITULO");
        document.getElementById("volver").addEventListener("click", window.close());
    }
}

function validarTel(tel) {
    return /^(\+34)?[6|9][0-9]{8}$/.test(tel);

}

function validarMail(mail) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail);
}

function calcularEdad() {
    let fecha = new Date(document.getElementById('birthday').value);
    let edad = new Date().getYear() - fecha.getYear();
    if (new Date().getMonth() < fecha.getMonth()) {
        edad--;
    } else if (new Date().getMonth() == fecha.getMonth()) {
        if (new Date().getDate() < fecha.getDate()) {
            edad--;
        }
    }
    return edad;
}

function validarFecha(fecha) {
    let fechaValida = new Date(fecha);
    if (fechaValida == 'Invalid Date') {
        return false;
    }
    if (fechaValida.getYear() > new Date().getYear()) {
        return false;
    }
    return true;
}

function cargarMunicipio(provincia) {
    let i = 0;
    let terr = document.getElementById('territorio').querySelectorAll('option');
    for (let index = 0; index < terr.length; index++) {
        if (terr[index].selected) {
            for (let index1 = 0; index1 < index - 1; index1++) {
                i += arrPro[index1].length;
            }
        }
    }

    let pro = document.getElementById('provincia').querySelectorAll('option');
    for (let index = 0; index < pro.length; index++) {
        if (pro[index].selected) {
            i += index - 1;
        }
    }
    console.log(i);

    let mun = document.getElementById('municipio');
    mun.innerHTML = '';
    let el = document.createElement('option');
    el.value = "0";
    el.innerHTML = "Seleccione Municipio";
    mun.appendChild(el);
    arr = arrMun[i];
    for (let index = 0; index < arr.length; index++) {
        let el = document.createElement('option');
        el.value = arr[index];
        el.innerHTML = arr[index];
        mun.appendChild(el);
    }
}

function cargarProvincia(territorio) {
    let pro = document.getElementById('provincia');
    pro.innerHTML = '';
    let arr;
    if (territorio == "euskadi") {
        arr = arrPro[0];
    } else if (territorio == "nafarroa") {
        arr = arrPro[1];
    } else {
        arr = arrPro[2];
    }
    let el = document.createElement('option');
    el.value = "0";
    el.innerHTML = "Seleccione Provincia";
    pro.appendChild(el);
    for (let index = 0; index < arr.length; index++) {
        let el = document.createElement('option');
        el.value = arr[index];
        el.innerHTML = arr[index];
        pro.appendChild(el);
    }
}

function moreThan50(palabra) {
    return palabra.length > 50;
}


function isLetter(car) {
    return car.toLowerCase() != car.toUpperCase();

}

function validarDni(dni) {
    if (dni.length != 9) {
        return false;
    } else {
        let numeros = dni.substring(0, dni.length - 1);
        if (isNaN(numeros)) {
            return false;
        } else {
            var letras = new Array('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
                'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T');
            if (dni.toUpperCase().charAt(dni.length - 1) != letras[numeros % 23]) {
                return false;
            }
        }
    }
    return true;

}