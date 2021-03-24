const path = require("path");
const sqlite3 = require("sqlite3").verbose();

class Database {
    constructor() {
        this._db_name = path.join(__dirname, "database", "tasks.db");
        this.db = new sqlite3.Database(this._db_name, err => {
            if (err) {
                return err;
            }
        });
        return this.db;
    }
}

module.exports = new Database;