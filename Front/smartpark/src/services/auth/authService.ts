import axios from 'axios';

interface ILoginData {
    email: string;
    password: string;
}

interface ILoginResponse {
    token: string;
}

class AuthService {

    private readonly baseUrl: string = 'http://localhost:8080/api/v1';

    async login(loginData: ILoginData): Promise<ILoginResponse | undefined> {
        try {
            const response = await axios.post(`${this.baseUrl}/login`, loginData);
            const loginResponse = response.data as ILoginResponse;
            await this.setToken(loginResponse.token);
            return loginResponse;
        } catch (error) {
            console.error('Login error:', error);
            return undefined;
        }
    }

    async logout(): Promise<void> {
        await localStorage.removeItem('authToken');
    }

    async isAuthenticated(): Promise<boolean> {
        const token = localStorage.getItem('authToken');
        return !!token;
    }

    private async setToken(token: string): Promise<void> {
        localStorage.setItem('authToken', token);
    }

    async getToken(): Promise<string | null> {
        return localStorage.getItem('authToken');
    }

    async getUser(): Promise<any | null> { // Adapt to your user data structure
        const token = await this.getToken();
        // If token exists, potentially fetch user data from backend using token
        // ... (implement logic based on your backend API)
        return null; // Return default value if user data not available
    }
}

export const authService = new AuthService();
