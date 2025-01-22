export class TokenModel {
    authToken: string = '';
    refreshToken: string = '';

    constructor(authToken: string, refreshToken: string) {

        if (authToken) {
            localStorage.setItem('accessToken', authToken);
            this.authToken = authToken;
        }
        else if (localStorage.getItem('accessToken')) {
            this.authToken = localStorage.getItem('accessToken') || '';
        }

        if (refreshToken) {
            localStorage.setItem('refreshToken', refreshToken);
            this.refreshToken = refreshToken;
        }
        else if (localStorage.getItem('refreshToken')) {
            this.refreshToken = localStorage.getItem('refreshToken') || '';
        }
    }

    setToken(authToken: string, refreshToken: string) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;

        localStorage.setItem('accessToken', authToken);
        localStorage.setItem('refreshToken', refreshToken);
    }

    getToken() {
        return { authToken: this.authToken, refreshToken: this.refreshToken };
    }

    removeToken() {
        this.authToken = '';
        this.refreshToken = '';
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
    }
}