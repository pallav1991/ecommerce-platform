export class TokenModel {
  authToken: string = '';
  refreshToken: string = '';

  constructor(authToken: string, refreshToken: string) {
    if (authToken) {
      if (typeof window !== 'undefined' && localStorage)
        localStorage.setItem('accessToken', authToken);
      this.authToken = authToken;
    } else if (
      typeof window !== 'undefined' &&
      localStorage &&
      localStorage.getItem('accessToken')
    ) {
      if (typeof window !== 'undefined' && localStorage)
        this.authToken = localStorage.getItem('accessToken') || '';
    }

    if (refreshToken) {
      if (typeof window !== 'undefined' && localStorage)
        localStorage.setItem('refreshToken', refreshToken);
      this.refreshToken = refreshToken;
    } else if (
      typeof window !== 'undefined' &&
      localStorage &&
      localStorage.getItem('refreshToken')
    ) {
      if (typeof window !== 'undefined' && localStorage)
        this.refreshToken = localStorage.getItem('refreshToken') || '';
    }
  }

  setToken(authToken: string, refreshToken: string) {
    this.authToken = authToken;
    this.refreshToken = refreshToken;

    if (typeof window !== 'undefined' && localStorage) {
      localStorage.setItem('accessToken', authToken);
      localStorage.setItem('refreshToken', refreshToken);
    }
  }

  getToken() {
    return { authToken: this.authToken, refreshToken: this.refreshToken };
  }

  removeToken() {
    this.authToken = '';
    this.refreshToken = '';
    if (typeof window !== 'undefined' && localStorage) {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
    }
  }
}
