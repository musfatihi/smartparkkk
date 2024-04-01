import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/reservations/My';

const ApiBooking = {
    async getBookings() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            return error;
        }
    },
};

export default ApiBooking;