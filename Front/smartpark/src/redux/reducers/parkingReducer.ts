import * as actionTypes from "../actions/actionTypes";
import {Parking} from "../../intrfcs/parking/parking";


export interface ParkingsState {
    parkings: Parking[];
    loading: boolean;
    error: string | null;
}

const initialState: ParkingsState = {
    parkings: [],
    loading: false,
    error: null,
};

const parkingReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case actionTypes.GET_PARKINGS:
            return {
                ...state,
                parkings: action.payload,
                loading: false,
                error: null,
            };
        case actionTypes.DELETE_PARKING:
            return {
                ...state,
                parkings: state.parkings.filter(parking => parking.id !== action.payload),
            };
        case actionTypes.CREATE_PARKING:
            return {
                ...state,
                parkings: [...state.parkings, action.payload],
                loading: false,
                error: null,
            };
        case actionTypes.UPDATE_PARKING:
            return {
                ...state,
                parkings: state.parkings.map(parking => {
                    if (parking.id === action.payload.id) {
                        return action.payload;
                    }
                    return parking;
                }),
                loading: false,
                error: null,
            };

        default:
            return state;
    }
}

export default parkingReducer;
