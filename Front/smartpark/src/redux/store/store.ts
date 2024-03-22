import {configureStore} from "@reduxjs/toolkit";
import rootReducer from "../reducers/RootState";
import {useDispatch} from "react-redux";

const token = localStorage.getItem('token');
const initialState = {
    auth: { token: token ?? null, error: null },
};

const store = configureStore({
    reducer: rootReducer,
    preloadedState: initialState,
});


export type AppDispatch = typeof store.dispatch;
export const useAppDispatch = () => useDispatch<AppDispatch>();

export default store;