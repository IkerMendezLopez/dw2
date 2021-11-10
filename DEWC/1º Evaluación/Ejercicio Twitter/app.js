const retryTweets = () => {
	if (localStorage.getItem('tweets')) {
		return JSON.parse(localStorage.getItem('tweets'))
	} else return []
}

const saveTweet = (tweet) => {
	db.push(tweet)
	localStorage.setItem('tweets', JSON.stringify(db))
}

const deleteTweet = (index) => {
	db.splice(index, 1)
	localStorage.setItem('tweets', JSON.stringify(db))
}

const db = retryTweets()
let btn = document.getElementById('twittear')
let txtArea = document.getElementById('tweet')

const drawTweets = () => {
	let listado = document.getElementById('lista-tweets')
	listado.innerHTML = ''
	db.forEach((tweet, index) => {
		listado.innerHTML += `
					<div class="tweet">
						<p>${tweet}</p>
						<button value="${index}" class="borrar-tweet">x</button>
					</div>`
	})

	let btnsBorrar = document.querySelectorAll('.borrar-tweet')

	btnsBorrar.forEach(btn => btn.addEventListener('click', () => {
		deleteTweet(btn.value)
		drawTweets()
	}))
}

drawTweets()

btn.addEventListener('click', (e) => {
	e.preventDefault()
	let tweet = txtArea.value
	if (tweet.length == 0) return
	saveTweet(tweet)
	drawTweets()
	txtArea.value = ''
})