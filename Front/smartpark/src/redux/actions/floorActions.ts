import { Dispatch } from 'redux';
import { CREATE_FLOOR, DELETE_FLOOR, GET_FLOORS, UPDATE_FLOOR } from './actionTypes';
import ApiFloor from "../api/ApiFloor";

export const getFloors = () => {
    return async (dispatch: Dispatch) => {
        try {
            const floors = await ApiFloor.getFloors();
            dispatch({ type: GET_FLOORS, payload: floors });
        } catch (error) {
            console.error("Error fetching floors:", error);
        }
    };
};

export const deleteFloor = (floorId: string) => {
    return async (dispatch: Dispatch) => {
        try {
            await ApiFloor.deleteFloor(floorId);
            dispatch({ type: DELETE_FLOOR, payload: floorId });
        } catch (error) {
            console.error("Error deleting floor:", error);
        }
    };
};

export const createFloor = (floorData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const createdFloor = await ApiFloor.createFloor(floorData);
            dispatch({ type: CREATE_FLOOR, payload: createdFloor });
            return createdFloor;
        } catch (error) {
            console.error("Error creating floor:", error);
            throw error;
        }
    };
};

export const updateFloor = (floorId: string, floorData: any) => {
    return async (dispatch: Dispatch) => {
        try {
            const updatedFloor = await ApiFloor.updateFloor(floorId, floorData);
            dispatch({ type: UPDATE_FLOOR, payload: updatedFloor });
        } catch (error) {
            console.error("Error updating floor:", error);
        }
    };
};
