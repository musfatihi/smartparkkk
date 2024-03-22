import { combineReducers } from 'redux';
import authReducer, {AuthState} from "./authReducer";
import parkingReducer, {ParkingsState} from "./parkingReducer";
import floorReducer, {FloorsState} from "./floorReducer";

export interface RootState {
    parking: ParkingsState;
    floor:FloorsState;
    auth: AuthState;
}

const rootReducer = combineReducers({
    auth: authReducer,
    parking:parkingReducer,
    floor:floorReducer
});

export default rootReducer;