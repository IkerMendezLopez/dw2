const divListaTweets = document.getElementById('lista-tweets');
document.getElementById('enviar').addEventListener('click', addTweet);
divListaTweets.addEventListener('click', deleteTweet);
document.addEventListener('DOMContentLoaded', cargarLS);

function addTweet(e) {
    e.preventDefault();
    let tweet = document.getElementById('tweet').value;
    let btnBorrar = document.createElement('a');
    btnBorrar.classList = 'borrar-tweet';
    btnBorrar.innerText = 'X';
    let li = document.createElement('li');
    li.innerText = tweet;
    li.appendChild(btnBorrar);
    divListaTweets.appendChild(li);
    addTweetLS(tweet);
}

function deleteTweet(e) {
    e.preventDefault();
    if (e.target.className == 'borrar-tweet') {
        e.target.parentElement.remove();
        deleteTweetLS(e.target.parentElement.innerText);
    }
}

function cargarLS() {
    let tweets;
    tweets = getTweetsLS();
    tweets.forEach(function (tweet) {
        let btnBorrar = document.createElement('a');
        btnBorrar.classList = 'borrar-tweet';
        btnBorrar.innerText = 'X';
        let li = document.createElement('li');
        li.innerText = tweet;
        li.appendChild(btnBorrar);
        divListaTweets.appendChild(li);
    });
}

function addTweetLS(tweet) {
    let tweets;
    tweets = getTweetsLS();
    tweets.push(tweet);
    localStorage.setItem('tweets', JSON.stringify(tweets));
}

function getTweetsLS() {
    let tweets;
    if (localStorage.getItem('tweets') == null) {
        tweets = [];
    } else {
        tweets = JSON.parse(localStorage.getItem('tweets'));
    }
    return tweets;
}


function deleteTweetLS(tweet) {
    let tweetBorrar = tweet.substring(0, tweet.length - 1);
    let tweets = getTweetsLS();
    let index =0;
    for(tweet of tweets){
        console.log(index);
        if (tweetBorrar == tweet) {
            tweets.splice(index, 1);
            break;
        }
        index++;
    }
    localStorage.setItem('tweets', JSON.stringify(tweets));
}