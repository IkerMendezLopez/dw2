const importeBase = 2000
const marcas = document.getElementById('marca')
const tipos = document.getElementsByName('tipo')

const anios = document.getElementById('anio')
for (let i = 1995; i <= new Date().getFullYear(); i++) {
	anios.innerHTML += `
		<option value="${i}">${i}</option>
	`
}

document.getElementById('enviar').addEventListener('click', e => {
	e.preventDefault()
	let marca = marcas[marcas.selectedIndex].value
	if (marcas.value == 0) return alert('Deves seleccionar una marca')
	let tipo = [...tipos].find(e => e.checked).value

	let importe = importeBase
	let incremento = 0
	switch (marca) {
	case 1:
		incremento = importeBase * 15 / 1000
		break
	case 2:
		incremento = importeBase * 5 / 1000

		break
	case 3:
		incremento = importeBase * 35 / 1000
		break
	}

	importe += incremento




	if (tipo == 'Basico') importe += importeBase * 30 / 1000
	else importe += importeBase * 35 / 1000

	let anio = anios[anios.selectedIndex].value
	anio = new Date().getFullYear() - anio

	importe = importe - (importe * (anio * 3) / 1000)
	let resultado = document.getElementById('resultado')
	let gifCarga = document.createElement('img')
	gifCarga.src = './img/spinner.gif'
	resultado.innerHTML = ''
	resultado.appendChild(gifCarga)
	setTimeout(() => {
		resultado.removeChild(gifCarga)
		resultado.innerHTML += `
		<div>
		<p class="header">Tu seguro:</p>
			<p>
			<strong>Marca</strong>
			${marca}
			</p>
			<p>
			<strong>Año</strong>
			${anio}
			</p>
			<p>
			<strong>Tipo seguro</strong>
			${tipo}
			</p>
			<p>
			<strong>Importe total</strong>
			 ${importe} €
			</p>
	</div>`
	}, 5000)



})