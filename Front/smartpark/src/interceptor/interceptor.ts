import axios from 'axios';
import { getTokenFromLocalStorage } from './authUtils';

const setupInterceptors = () => {
  const requestInterceptor = axios.interceptors.request.use(
    (config) => {
      const token = getTokenFromLocalStorage();
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );

  const responseInterceptor = axios.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response.status === 401) {
        console.error('Unauthorized:', error.response.data.error);
      }
      return Promise.reject(error);
    }
  );

  return () => {
    axios.interceptors.request.eject(requestInterceptor);
    axios.interceptors.response.eject(responseInterceptor);
  };
};

export default setupInterceptors;