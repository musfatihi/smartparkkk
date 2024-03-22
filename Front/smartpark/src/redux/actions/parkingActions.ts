import { Dispatch } from 'redux';
import { CREATE_PARKING, DELETE_PARKING, GET_PARKINGS, UPDATE_PARKING } from './actionTypes';
import ApiParking from "../api/ApiParking";


export const getParkings = () => {
    return async (dispatch: Dispatch) => {
        try {
            const parkings = await ApiParking.getParkings();
            dispatch({ type: GET_PARKINGS, payload: parkings });
        } catch (error) {
            console.error("Error fetching parkings:", error);
        }
    };
};

export const deleteParking = (parkingId: string) => {
    return async (dispatch: Dispatch) => {
        try {
            await ApiParking.deleteParking(parkingId);
            dispatch({ type: DELETE_PARKING, payload: parkingId });
        } catch (error) {
            console.error("Error deleting parking:", error);
        }
    };
};

export const createParking = (parkingData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const createdParking = await ApiParking.createParking(parkingData);
            dispatch({ type: CREATE_PARKING, payload: createdParking });
            return createdParking;
        } catch (error) {
            console.error("Error creating parking:", error);
            throw error;
        }
    };
};

export const updateParking = (parkingId: string, parkingData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const updatedParking = await ApiParking.updateParking(parkingId, parkingData);
            dispatch({ type: UPDATE_PARKING, payload: updatedParking });
        } catch (error) {
            console.error("Error updating parking:", error);
        }
    };
};
