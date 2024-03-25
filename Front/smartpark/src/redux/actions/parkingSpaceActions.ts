import {Dispatch} from "redux";
import ApiParkingSpace from "../api/ApiParkingSpace";
import {CREATE_PARKING_SPACE, DELETE_PARKING_SPACE, GET_PARKING_SPACES, UPDATE_PARKING_SPACE} from "./actionTypes";


export const getParkingSpaces = () => {
    return async (dispatch: Dispatch) => {
        try {
            const parkingSpaces = await ApiParkingSpace.getParkingSpaces();
            dispatch({ type: GET_PARKING_SPACES, payload: parkingSpaces });
        } catch (error) {
            console.error("Error fetching parking Spaces:", error);
        }
    };
};

export const deleteParkingSpace = (parkingSpaceId: string) => {
    return async (dispatch: Dispatch) => {
        try {
            await ApiParkingSpace.deleteParkingSpace(parkingSpaceId);
            dispatch({ type: DELETE_PARKING_SPACE, payload: parkingSpaceId });
        } catch (error) {
            console.error("Error deleting parking Space:", error);
        }
    };
};

export const createParkingSpace = (parkingSpaceData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const createdParkingSpace = await ApiParkingSpace.createParkingSpace(parkingSpaceData);
            dispatch({ type: CREATE_PARKING_SPACE, payload: createdParkingSpace });
            return createdParkingSpace;
        } catch (error) {
            console.error("Error creating parking space:", error);
            throw error;
        }
    };
};

export const updateParkingSpace = (parkingSpaceId: string, parkingSpaceData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const updatedParkingSpace = await ApiParkingSpace.updateParkingSpace(parkingSpaceId, parkingSpaceData);
            dispatch({ type: UPDATE_PARKING_SPACE, payload: updatedParkingSpace });
        } catch (error) {
            console.error("Error updating parking space:", error);
        }
    };
};
