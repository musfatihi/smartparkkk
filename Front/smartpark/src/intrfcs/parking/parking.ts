import {Floor} from "../floor/floor";

export interface Parking {
    id: string;
    name: string;
    city: string;
    floors:Floor[];
}