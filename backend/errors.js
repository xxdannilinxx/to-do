class DBError extends Error {
    construct() {
        this.name = 'DBError';
    }
}
module.exports = new DBError;