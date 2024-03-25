import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/parkingspaces';

const ApiParkingSpace = {
    async getParkingSpaces() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async deleteParkingSpace(parkingSpaceId: string) {
        try {
            await axios.delete(`${API_URL}/${parkingSpaceId}`);
        }
        catch (error) {
            return error;
        }
    },

    async createParkingSpace(parkingSpaceData: any) {
        try {
            const response = await axios.post(API_URL, parkingSpaceData);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async updateParkingSpace(parkingSpaceId: string, parkingSpaceData: any) {
        try {
            await axios.put(`${API_URL}/${parkingSpaceId}`, parkingSpaceData
            );
        }
        catch (error) {
            return error;
        }
    }
};

export default ApiParkingSpace;