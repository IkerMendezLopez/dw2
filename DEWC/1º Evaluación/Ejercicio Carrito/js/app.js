const retryShopCar  = () => {
	return localStorage.getItem('carrito') ? JSON.parse(localStorage.getItem('carrito')) : []
}

const drawShopCar = () => {

}






const db = retryShopCar()

document.querySelectorAll('agregar-carrito').forEach(btn => {
	btn.addEventListener()
})


