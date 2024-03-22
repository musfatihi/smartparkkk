import * as actionTypes from "../actions/actionTypes";
import {Floor} from "../../intrfcs/floor/floor";


export interface FloorsState {
    floors: Floor[];
    loading: boolean;
    error: string | null;
}

const initialState: FloorsState = {
    floors: [],
    loading: false,
    error: null,
};

const floorReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case actionTypes.GET_FLOORS:
            return {
                ...state,
                floors: action.payload,
                loading: false,
                error: null,
            };
        case actionTypes.DELETE_FLOOR:
            return {
                ...state,
                floors: state.floors.filter(floor => floor.id !== action.payload),
            };
        case actionTypes.CREATE_FLOOR:
            return {
                ...state,
                floors: [...state.floors, action.payload],
                loading: false,
                error: null,
            };
        case actionTypes.UPDATE_FLOOR:
            return {
                ...state,
                floors: state.floors.map(floor => {
                    if (floor.id === action.payload.id) {
                        return action.payload;
                    }
                    return floor;
                }),
                loading: false,
                error: null,
            };

        default:
            return state;
    }
}

export default floorReducer;
