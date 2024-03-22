import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/floors';

const ApiFloor = {
    async getFloors() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async deleteFloor(floorId: string) {
        try {
            await axios.delete(`${API_URL}/${floorId}`);
        }
        catch (error) {
            return error;
        }
    },

    async createFloor(floorData: any) {
        try {
            const response = await axios.post(API_URL, floorData);
            return response.data;
        } catch (error) {
            return error;
        }
    },

    async updateFloor(floorId: string, floorData: any) {
        try {
            await axios.put(`${API_URL}/${floorId}`, floorData
            );
        }
        catch (error) {
            return error;
        }
    }
};

export default ApiFloor;