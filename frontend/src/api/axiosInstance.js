import axios from 'axios';

export const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api', // Backend URL
  headers: {
    'Content-Type': 'application/json',
  },
});