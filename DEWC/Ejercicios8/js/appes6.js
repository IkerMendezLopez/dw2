document.getElementById("enviar").addEventListener("click", (e) =>{
    e.preventDefault();
    let seguro = 2000;

    let marca = document.getElementById("marca").value;
    let errores = [];
    switch (parseInt(marca)) {
        case 1:
            seguro+= seguro*0.15;
            break;
        case 2:
            seguro+= seguro*0.5;
            break;
        case 3:
            seguro+= seguro*0.35;
            break;
        default:
            errores.push("marca");
            break;
    }

    let tipos = document.getElementsByName("tipo");
    if(tipos[0].checked){
        seguro+= seguro*0.30;
    }else{
        seguro+= seguro*0.50;
    }

    let anio = document.getElementById("anio").value;

    if(isNaN(anio)){
        errores.push("año");
    }else{
        seguro-=seguro*(0.03*anio);
    }

    switch (errores.length) {
        case 1:
            alert("Selecciona el campo " + errores[0]);
            break;
        case 2:
            alert("Selecciona los campos " + errores[0] + " y " +errores[1]);
            break;
        default:
            document.getElementsByTagName("img")[0].style.display="block";
            setTimeout(() => {
                document.getElementsByTagName("img")[0].style.display="none";
                let div =document.getElementById("resultado");
                let divInt = document.createElement("div");
                div.appendChild(divInt);
                let titulo = document.createElement("p");
                titulo.setAttribute("class", "header");
                titulo.innerHTML="Resultado";
                divInt.appendChild(titulo);
                let marcaP = document.createElement("p");
                marcaP.innerHTML = "Marca: " + document.getElementById("marca")[document.getElementById("marca").selectedIndex].innerHTML;
                divInt.appendChild(marcaP);
                // VOY POR AQUI
                // let anioP = document.createElement("p");
                // anioP.innerHTML = "Año: " +[...document.getElementsByName("tipo")].find(e => e.checked).value;
                // divInt.appendChild(anioP);
                let tipoP = document.createElement("p");
                tipoP.innerHTML = "Tipo: " +[...document.getElementsByName("tipo")].find(e => e.checked).value;
                divInt.appendChild(tipoP);
            }, 5000);
            break;
    }
});

let select = document.getElementById("anio");
let option = document.createElement("option");
option.innerHTML = "- Seleccionar -";
select.appendChild(option);
let fecha = new Date();
for (let index = fecha.getFullYear(); index >= 2000; index--) {
    option = document.createElement("option");
    option.value= fecha.getFullYear()-(index-1);
    option.innerHTML = index;
    select.appendChild(option);
}