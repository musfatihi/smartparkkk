import React, { useState, useEffect } from 'react';
import {Client} from "../../intrfcs/client/client";
import {getClients} from "../../services/client/clientService";

const ClientList = () => {
    const [clients, setClients] = useState<Client[]>([]);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            try {
                const data = await getClients();
                setClients(data);
            } catch (error) {
                console.error(error); // Handle errors appropriately
            } finally {
                setIsLoading(false);
            }
        };

        fetchData();
    }, []);

    if (isLoading) return <p>Loading...</p>;

    return (
        <ul>
            {clients.map((client) => (
                <li key={client.id}>
                    {/* Display client details */}
                    <h2>{client.email}</h2>
                    <h2>{client.id}</h2>
                    {/* Add optional details like contact number, address, etc. */}
                </li>
            ))}
        </ul>
    );
};

export default ClientList;
