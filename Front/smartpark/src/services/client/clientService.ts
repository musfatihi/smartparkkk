import axios from 'axios';
import {Client} from "../../intrfcs/client/client";

const baseUrl = 'http://localhost:8080/api/v1/clients';

// Fetch all clients
export const getClients = async () => {
    const response = await axios.get(baseUrl);
    return response.data;
}

// Get a single client by ID
export const getClient = async (id: number) => {
    const response = await axios.get(`${baseUrl}/${id}`);
    return response.data;
}

// Create a new client
export const createClient = async (client: Client) => {
    const response = await axios.post(baseUrl, client);
    return response.data;
}

// Update a client
export const updateClient = async (client: Client) => {
    const response = await axios.put(`${baseUrl}/${client.id}`, client);
    return response.data;
}

// Delete a client
export const deleteClient = async (id: number) => {
    await axios.delete(`${baseUrl}/${id}`);
}
