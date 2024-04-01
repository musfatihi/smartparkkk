import {ParkingSpace} from "../parkingSpace/parkingspace";

export interface Floor {
    id:string;
    nbr:number;
    parking:string;
    parkingName:string;
    parkingSpaces:ParkingSpace[];
}