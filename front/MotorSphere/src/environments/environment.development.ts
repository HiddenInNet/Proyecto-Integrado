export const environment = {
    SERVER_URL: "http://localhost:8081",

    getJWTFromLocalStorage() {
        return localStorage.getItem('user_jwt');
    },
};
