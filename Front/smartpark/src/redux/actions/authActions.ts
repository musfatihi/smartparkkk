import axios from 'axios';
import { Dispatch } from 'redux';
import { LOGIN_SUCCESS, LOGIN_FAILURE, LOGOUT } from './actionTypes';

interface LoginResponse {
    token: string;
}

interface LoginPayload {
    email: string;
    password: string;
}

export const login = (email: string, password: string) => {
    return async (dispatch: Dispatch) => {
        try {
            const loginPayload: LoginPayload = { email, password};
            const endpoint = 'http://localhost:8080/api/v1/login';
            const response = await axios.post<LoginResponse>(endpoint, loginPayload);
            const token = response.data.token;
            localStorage.setItem('token', token);
            dispatch({ type: LOGIN_SUCCESS, payload: token });
        } catch (error) {
            dispatch({ type: LOGIN_FAILURE, payload: 'Invalid username or password' });
        }
    };
};

export const logout = () => {
    localStorage.removeItem('token');
    return { type: LOGOUT };
};