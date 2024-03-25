import * as actionTypes from "../actions/actionTypes";
import {ParkingSpace} from "../../intrfcs/parkingSpace/parkingspace";

export interface ParkingSpacesState {
    parkingSpaces: ParkingSpace[];
    loading: boolean;
    error: string | null;
}

const initialState: ParkingSpacesState = {
    parkingSpaces: [],
    loading: false,
    error: null,
};

const parkingSpaceReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case actionTypes.GET_PARKING_SPACES:
            return {
                ...state,
                parkingSpaces: action.payload,
                loading: false,
                error: null,
            };
        case actionTypes.DELETE_PARKING_SPACE:
            return {
                ...state,
                parkingSpaces: state.parkingSpaces.filter(parkingSpace => parkingSpace.id !== action.payload),
            };
        case actionTypes.CREATE_PARKING_SPACE:
            return {
                ...state,
                parkingSpaces: [...state.parkingSpaces, action.payload],
                loading: false,
                error: null,
            };
        case actionTypes.UPDATE_PARKING_SPACE:
            return {
                ...state,
                parkingSpaces: state.parkingSpaces.map(parkingSpace => {
                    if (parkingSpace.id === action.payload.id) {
                        return action.payload;
                    }
                    return parkingSpace;
                }),
                loading: false,
                error: null,
            };

        default:
            return state;
    }
}

export default parkingSpaceReducer;
