"use strict";


const depositBtn = document.querySelector("#deposit-btn");
const withdrawBtn = document.querySelector("#withdraw-btn");




depositBtn.addEventListener('click', () => {
    // const name = document.querySelector('#name').value
    // const amount = Number(document.querySelector('#amount').value)


    fetchData();
});

withdrawBtn.addEventListener('click', () => {
    
});


const fetchData = () => {
    fetch("http://localhost:8080/accounts/all")
    .then(data => data.json())
    .then(json => console.log(json))
    .catch(error => console.log(error));
};