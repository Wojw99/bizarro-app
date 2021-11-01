const express = require('express');
const app = express();
const csv = require('csv-parser');
const fs = require('fs');
const { spawn } = require('child_process');

app.use(express.json());

var records = [
    {
        id: 0,
        name: "Ekstra rower górski",
        body: "Ten rower górski jest tak Górski, że Robert mógłby się zawstydzić.",
        creationDate: "21-04-2021",
        city: "Paniówki",
        province: "śląskie",
        price: 325.5,
        type: "górski",
    },
    {
        id: 1,
        name: "Super rower górski",
        body: "Co zarower?!!!??!.11?",
        creationDate: "22-04-2021",
        city: "Gliwice",
        province: "śląskie",
        price: 425.5,
        type: "górski",
    },
    {
        id: 2,
        name: "Świetny rower górski",
        body: "Sprzedam majestatyczny i wygodny rower.",
        creationDate: "21-05-2021",
        city: "Dąbrowa Górnicza",
        province: "śląskie",
        price: 255.5,
        type: "górski",
    },
    {
        id: 3,
        name: "Majestatyczny rower gravel",
        body: "A sprzedawca jeszcze bardziej.",
        creationDate: "25-05-2021",
        city: "Łódź",
        province: "łódzkie",
        price: 100.0,
        type: "gravel",
    },
    {
        id: 4,
        name: "Kozacki rower górski",
        body: "Kozaków proszę nie mylić z tatarami.",
        creationDate: "23-05-2021",
        city: "Warszawa",
        province: "mazowieckie",
        price: 112.0,
        type: "gravel",
    },
];

// - - - - - GET main route - - - - - */
app.get('/', function (request, response) {
    response.send('Hello Bizarro!');
});

// - - - - - GET for all notes - - - - - */
app.get('/api/records', function (request, response) {
    var page = request.query.page;
    var limit = request.query.limit;
    response.send(records);
});

// - - - - - - - RUN SERVER - - - - -  - - 
app.listen(3000, function () {
    console.log('Listening on port 3000...');
});