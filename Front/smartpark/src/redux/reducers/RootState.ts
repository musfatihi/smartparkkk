import { combineReducers } from 'redux';
import authReducer, {AuthState} from "./authReducer";
import parkingReducer, {ParkingsState} from "./parkingReducer";
import floorReducer, {FloorsState} from "./floorReducer";
import parkingSpaceReducer, {ParkingSpacesState} from "./parkingSpaceReducer";

export interface RootState {
    parking: ParkingsState;
    parkingSpace:ParkingSpacesState;
    floor:FloorsState;
    auth: AuthState;
}

const rootReducer = combineReducers({
    auth: authReducer,
    parking:parkingReducer,
    floor:floorReducer,
    parkingSpace:parkingSpaceReducer
});

export default rootReducer;