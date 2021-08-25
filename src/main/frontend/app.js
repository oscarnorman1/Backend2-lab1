"use strict";


const depositBtn = document.querySelector("#deposit-btn");
const withdrawBtn = document.querySelector("#withdraw-btn");




depositBtn.addEventListener('click', () => {
    const name = document.querySelector('#name').value
    const amount = Number(document.querySelector('#amount').value)

    fetchData(name, amount);
});

withdrawBtn.addEventListener('click', () => {
    
});

const fetchData = (name, amount) => {

    fetch(`http://localhost:8080/accounts/deposit/${name}/${amount}`)
        .then((data) => console.log(data))
        .catch((error) => console.log(error));
};