const recuperarCursos = () => {
	let items =  localStorage.getItem('cursos')
	if(items) {
		cursos = JSON.parse(items) 
	}
	else return cursos = {}
}

const guardarCursos = () => {
	localStorage.setItem('cursos', JSON.stringify(cursos))
}

var cursos = {}


const vaciarCarrito = () => {
	localStorage.removeItem('cursos')
	cursos = {}
	guardarCursos()
	dibujarCarrito()
}

const dibujarCarrito = () => {
	let tabla = document.getElementById('lista-carrito')
	tabla.innerHTML = ''
	for (const curso in cursos) {
		if (Object.hasOwnProperty.call(cursos, curso)) {
			const element = cursos[curso]
			let { img, nombre, precio, cantidad } = element
			tabla.innerHTML += `
				<tr>
					<td>
						<img src="${img}">
					</td>
					<td>
						${nombre}
					</td>
					<td>
					${precio}
					</td>
					<td>
					${cantidad}
					</td>
				</tr>
			` 
			
		}
	}
}

recuperarCursos()
dibujarCarrito()

document.getElementById('vaciar-carrito').addEventListener('click', vaciarCarrito)

let addButtons = document.querySelectorAll('.agregar-carrito')
addButtons.forEach(btn => {
	btn.addEventListener('click', () => {
		let curso = btn.parentElement
		let nombre = curso.querySelector('h4')
		let img = curso.previousElementSibling.src
		let precio = curso.querySelector('.precio')
		let id = btn.getAttribute('data-id')
		nombre = nombre.textContent
		precio = precio.querySelector('span').textContent

		if(!cursos[id]) {
			cursos[id] = {'nombre': nombre, 'precio': precio, 'img': img, 'cantidad': 1}
		} else cursos[id].cantidad++
		guardarCursos()
		dibujarCarrito()
	})
})