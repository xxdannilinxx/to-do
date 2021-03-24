const express = require("express");

const app = express();
const database = require('./database');

app.listen(3000);

app.get('/tasks/', async (req, res) => {
    try {
        const sql = `SELECT * FROM Tasks ORDER BY date ASC`;
        await database.all(sql, [], (error, rows) => {
            if (error) {
                return res.status(500).send({ error: error.message });
            }
            return res.status(200).send(rows);
        });
    } catch (error) {
        return res.status(500).send({ error: error.message });
    }
});

app.get('/tasks/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const sql = `SELECT * FROM Tasks WHERE id = ? ORDER BY date ASC`;
        await database.get(sql, [id], (error, row) => {
            if (error) {
                return res.status(500).send({ error: error.message });
            }
            return res.status(200).send(row);
        });
    } catch (error) {
        return res.status(500).send({ error: error.message });
    }
});

app.post('/tasks', async (req, res) => {
    try {
        const dados = [req.query.description, req.query.date];
        const sql = `INSERT INTO Tasks (description, date) VALUES (?, ?)`;
        await database.run(sql, dados, error => {
            if (error) {
                return res.status(500).send({ error: error.message });
            }
            return res.status(201);
        });
    } catch (error) {
        return res.status(500).send({ error: error.message });
    }
});

app.put('/tasks/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const dados = [req.query.description, req.query.date, id];
        const sql = `UPDATE Tasks SET description = ?, date = ? WHERE (id = ?)`;
        await database.run(sql, dados, error => {
            if (error) {
                return res.status(500).send({ error: error.message });
            }
            return res.status(202);
        });
    } catch (error) {
        return res.status(500).send({ error: error.message });
    }
});

app.delete('/tasks/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const sql = `DELETE FROM Tasks WHERE id = ?`;
        await database.run(sql, [id], error => {
            if (error) {
                return res.status(500).send({ error: error.message });
            }
            return res.status(202);
        });
    } catch (error) {
        return res.status(500).send({ error: error.message });
    }
});