"use strict";

const depositBtn = document.querySelector("#deposit-btn");
const withdrawBtn = document.querySelector("#withdraw-btn");
const showInfo = document.querySelector('#show-info');

depositBtn.addEventListener('click', () => {
    const name = document.querySelector('#name').value
    const amount = Number(document.querySelector('#amount').value)

    depositFetch(name, amount);
});

withdrawBtn.addEventListener('click', () => {
    const name = document.querySelector('#name').value
    const amount = Number(document.querySelector('#amount').value)

    withdrawFetch(name, amount);
});

const depositFetch = (name, amount) => {
    fetch(`http://localhost:8080/accounts/deposit/${name}/${amount}`)
        .then(data => data.json())
        .then(json => updateInfo(json.name, json.balance))
        .catch((error) => console.log(error));
};

const withdrawFetch = (name, amount) => {
    fetch(`http://localhost:8080/accounts/withdraw/${name}/${amount}`)
        .then((data) => console.log(data))
        .catch((error) => console.log(error));
}

const updateInfo = (name, balance) => {
    showInfo.innerText = '';
    showInfo.innerText = `${name}: ${balance} kr`;
}