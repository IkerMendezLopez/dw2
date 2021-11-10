const getLetraDNI = (numeros) => {
	const letras = new Array('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
		'H',
		'L',
		'C', 'K', 'E', 'T')

	let clave = numeros % 23
	return letras[clave]
}


const inputNombre = document.getElementById('nombre')
const inputDNI = document.getElementById('dni')
const inputLetra = document.getElementById('letra')
const inputOpinion = document.getElementById('opinion')
const inputMaxChar = document.getElementById('maxChar')
const radioSexo = document.getElementsByName('sexo')
const radioSueldo = document.getElementsByName('sueldo')
const inputFichero = document.getElementById('archivo')
const selectProvincia = document.getElementById('provincia')
const selectHobbys = document.getElementById('hobbys')



const comprobacionDNI = () => {
	if (inputDNI.value.length != 8) {
		alert('El DNI deve tener 8 caracteres de longitud!')
		inputDNI.focus()

	} else {
		inputLetra.value = getLetraDNI(inputDNI.value)
		inputLetra.classList.add('bg-success')
	}
}

const limitarOpinion = () => {
	if(inputOpinion.value.length >= inputMaxChar.value){
		inputOpinion.value = inputOpinion.value.slice(0, inputMaxChar.value)
	}
}

const comprobarMaxChar =  () => {
	if(isNaN(inputMaxChar.value)) {
		alert('El maximo de caracteres deve ser un número')
		inputMaxChar.value = ''
		inputMaxChar.focus()
	}
	
}

const comprobarFormulario = () => {
	if(inputNombre.value.length == 0) return false
	if(inputDNI.value.length < 8) return false
	let radioSelected = false
	radioSexo.forEach(e => e.checked ? radioSelected = true : 0)
	if(!radioSelected) return false 
	if(selectProvincia.selectedIndex == 0) return false
	return true
}


window.onload = () => {
	inputNombre.focus()
	inputLetra.readOnly = true
	inputDNI.addEventListener('change', comprobacionDNI)

	inputMaxChar.addEventListener('change', comprobarMaxChar)

	inputOpinion.addEventListener('input', limitarOpinion)

	radioSueldo.forEach(e => e.value =='de 10000€ a 20000€' ? e.checked = true : 0)

	document.getElementById('limpiar').addEventListener('click', (e) => {
		let displayDatos = document.querySelector('#displayDatos')
		displayDatos.innerHTML = ''
		displayDatos.classList.add('d-none')
		
		e.preventDefault()
	})

	document.getElementById('enviar').addEventListener('click', (e) => {
		e.preventDefault()
		if(comprobarFormulario()){

			let displayDatos = document.querySelector('#displayDatos')
			displayDatos.classList.remove('d-none')
			displayDatos.innerHTML += '<h2>Datos del formulario</h2>'
			displayDatos.innerHTML += `<strong>Nombre</strong> ${inputNombre.value}<br>`
			displayDatos.innerHTML += `<strong>DNI</strong> ${inputDNI.value}${inputLetra.value}<br>`
			displayDatos.innerHTML += `<strong>Opinion</strong> ${inputOpinion.value}<br>`
			displayDatos.innerHTML += `<strong>Longitud opinion</strong> ${inputOpinion.value.length}<br>`
			let sexo = document.querySelector('input[name="sexo"]:checked').value
			displayDatos.innerHTML += `<strong>Sexo</strong> ${sexo}<br>`
			let sueldo = document.querySelector('input[name="sueldo"]:checked').value
			displayDatos.innerHTML += `<strong>Sueldo</strong> ${sueldo}<br>`
			displayDatos.innerHTML += `<strong>Fichero</strong> ${inputFichero.files.length > 0 ? inputFichero.files[0].name : ''}<br>`
			displayDatos.innerHTML += `<strong>Provincia</strong> ${selectProvincia[selectProvincia.selectedIndex].text} - ${selectProvincia[selectProvincia.selectedIndex].value}<br>`
			displayDatos.innerHTML += `<strong>Hobbies</strong> ${[...selectHobbys].filter(e => e.selected ? e.value : '').map(e => e.value).join(',')}<br>`
			

			
		}else {
			alert('Deves rellanar todos los campos obligatorios')
		}
	})
}