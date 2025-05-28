function miFunc(e) {
    alert(e.target.textContent);
}

document.querySelector('buy-button').addEventListener('click', miFunc);