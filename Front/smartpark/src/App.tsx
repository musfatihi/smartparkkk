import React from 'react';
import LoginPage from "./pages/LoginPage";
import store from "./redux/store/store";
import {Provider, useSelector} from "react-redux";
import RegisterForm from "./components/register/RegisterForm";
import Navbar from "./components/Navbar";
import AppRouter from "./routes/AppRouter";
import {RootState} from "./redux/reducers/RootState";

const ProtectedRoutes = () => {
    const token = useSelector((state: RootState) => state.auth.token);

    return token ? <AppRouter /> : <LoginPage />;
};

function App() {
  return (
      <Provider store={store}>
          <ProtectedRoutes />
      </Provider>
  );
}

export default App;
