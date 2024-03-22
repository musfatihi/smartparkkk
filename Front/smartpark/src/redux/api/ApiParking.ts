import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/parkings';

const ApiParking = {
    async getParkings() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async deleteParking(parkingId: string) {
        try {
            await axios.delete(`${API_URL}/${parkingId}`);
        }
        catch (error) {
            return error;
        }
    },

    async createParking(parkingData: any) {
        try {
            const response = await axios.post(API_URL, parkingData);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async updateParking(parkingId: string, parkingData: any) {
        try {
            await axios.put(`${API_URL}/${parkingId}`, parkingData
            );
        }
        catch (error) {
            return error;
        }
    }
};

export default ApiParking;