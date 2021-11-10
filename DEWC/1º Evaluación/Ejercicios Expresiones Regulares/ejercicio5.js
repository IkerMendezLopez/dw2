const checkDNI = () => {
	let dni = document.getElementById('dni').value
	if (!/^\d{8}[A-Z]$/.test(dni)) return false
	const letras = new Array('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
		'H',
		'L',
		'C', 'K', 'E', 'T')
	if(!/^\d{8}[a-zA-Z]$/.test(dni)) return false
	let split = dni.split()
	let num = dni.slice(0, split.length - 2)
	let letra = dni[dni.length - 1]
	return letra == letras[num % 23]
}


const comprobarEmail = () => {
	let email = document.getElementById('email').value
	return /\S+@\S+\.\S+/.test(email)
}

const comprobarCodigoPostal = ()  => {
	let postal = document.getElementById('postal').value
	return /\b\d{5}\b/.test(postal)
}

const comprobarFecha = () => {
	let fecha = document.getElementById('fecha').value
	if(!/(0?[1-9]|[12]\d|30|31)[^\w\d\r\n:](0?[1-9]|1[0-2])[^\w\d\r\n:](\d{4}|\d{2})/.test(fecha)) return false
	console.log(fecha)
	fecha = fecha.split('/').reverse().join('/')
	let date = new Date(fecha)
	return date != 'Invalid Date'
}

const comprobaFormulario = () => {


	if (!checkDNI()) return false
	if(!comprobarEmail) return false
	if(!comprobarCodigoPostal()) return false
	if(!comprobarFecha()) return false
	return true
}



document.addEventListener('submit', (e) => {
	e.preventDefault()
	if(comprobaFormulario()) alert('El formulario es correcto')
	else alert('El formulario es incorrecto')
})

