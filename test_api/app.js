const express = require('express');
const app = express();
const csv = require('csv-parser');
const fs = require('fs');
const { spawn } = require('child_process');

app.use(express.json());

var records = [
    {
        id: 0,
        userId: 0,
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
        userId: 0,
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
        userId: 0,
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
        userId: 1,
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
        userId: 2,
        name: "Kozacki rower górski",
        body: "Kozaków proszę nie mylić z tatarami.",
        creationDate: "23-05-2021",
        city: "Warszawa",
        province: "mazowieckie",
        price: 112.0,
        type: "gravel",
    },
];

var profiles = [
    {
        id: 0,
        userId: 0,
        email: "karol@bizarro.com",
        phone: "666555444",
        firstName: "Karol",
        lastName: "Śląski",
        creationDate: "20-04-2021",
        description: "Jestem ze Śląska",
    },
    {
        id: 4,
        userId: 1,
        email: "aleksandra@bizarro.com",
        phone: "333444111",
        firstName: "Aleksandra",
        lastName: "Łódzka",
        creationDate: "19-04-2021",
        description: "Pływam łodzią",
    },
    {
        id: 5,
        userId: 2,
        email: "tomasz@bizarro.com",
        phone: "111222333",
        firstName: "Tomasz",
        lastName: "Mazowiecki",
        creationDate: "18-04-2021",
        description: "Fascynat rowerów górski i wieloletni członek klubu rowerowego \"Hamulcowi Tarczownicy\"",
    },
];

var opinions = [
    {
        id: 0,
        userId: 0,
        creationDate: "20-04-2021",
        rating: 1,
        content: "Nie polecam tego użytkownika. Skłamał w opisie produktu.",
    },
    {
        id: 1,
        userId: 0,
        creationDate: "23-04-2021",
        rating: 2,
        content: "xxxxxxxx:::::!!!!????",
    },
    {
        id: 2,
        userId: 0,
        creationDate: "25-04-2021",
        rating: 5,
        content: "Wspaniały człowiek, od razu widać, że fascynat!",
    },
    {
        id: 3,
        userId: 1,
        creationDate: "27-04-2021",
        rating: 4,
        content: "Z Panią Olą z Łidzi zawsze idzie się dogadać.",
    },
    {
        id: 4,
        userId: 1,
        creationDate: "28-04-2021",
        rating: 4,
        content: "Transakcja przebiegła pomyślnie, polecam!",
    },
    {
        id: 5,
        userId: 2,
        creationDate: "24-04-2021",
        rating: 1,
        content: "Pan Tomasz nie zjawił się na umówionym miejscu przekazania towaru.",
    },
];

// - - - - - GET main route - - - - - */
app.get('/', function (request, response) {
    response.send('Hello Bizarro!');
});

// - - - - - GET for all records - - - - - */
app.get('/api/records', function (request, response) {
    var qPage = request.query.page;
    var qLimit = request.query.limit;
    var qCity = request.query.city;
    var qProvince = request.query.province;
    var qType = request.query.type;

    var filtered = records;

    if (qCity != undefined) {
        filtered = filtered.filter(function (value) {
            return value.city.toLowerCase().trim() == qCity.toString().toLowerCase().trim();
        });
    }

    if (qProvince != undefined) {
        filtered = filtered.filter(function (value) {
            return value.province.toLowerCase().trim() == qProvince.toString().toLowerCase().trim();
        });
    }

    if (qType != undefined) {
        filtered = filtered.filter(function (value) {
            return value.type.toLowerCase().trim() == qType.toString().toLowerCase().trim();
        });
    }

    response.send(filtered);
});

// - - - - - GET for record details - - - - - */
app.get('/api/records/:recordId', function (request, response) {
    var pRecordId = request.params.recordId;

    var filtered = records.filter(function (value) {
        return value.id == pRecordId;
    });

    response.send(filtered[0]);
});

// - - - - - DELETE record details - - - - - */
app.delete('/api/records/:recordId', function (request, response) {
    var pRecordId = request.params.recordId;

    var withoutDeleted = records.filter(function (value) {
        return value.id != pRecordId;
    });
    records = withoutDeleted;

    response.send("Succes");
});

// - - - - - PUT record details - - - - - */
app.put('/api/records', function (request, response) {
    var record = request.body;

    if (record.id < records.length) {
        records[record.id] = record;
        response.send(record);
    }

    response.send("Unable to PUT given record!");
});


// - - - - - POST user opinions - - - - - */
app.post('/api/records', function (request, response) {
    var record = request.body;
    record.id = records.length;

    records.push(record);

    response.send(record);
});


// - - - - - GET for single profile - - - - - */
app.get('/api/users/:userId/profile', function (request, response) {
    var pUserId = request.params.userId;

    var filtered = profiles.filter(function (value) {
        return value.userId == pUserId;
    });

    response.send(filtered[0]);
});

// - - - - - GET for user opinions - - - - - */
app.get('/api/users/:userId/opinions', function (request, response) {
    var pUserId = request.params.userId;

    var filtered = opinions.filter(function (value) {
        return value.userId == pUserId;
    });

    response.send(filtered);
});

// - - - - - POST for user opinions - - - - - */
app.post('/api/opinions', function (request, response) {
    var opinion = request.body;
    opinion.id = opinions.length;

    opinions.push(opinion);

    response.send(opinion);
});

// - - - - - - - RUN SERVER - - - - -  - - 
app.listen(3000, function () {
    console.log('Listening on port 3000...');
});