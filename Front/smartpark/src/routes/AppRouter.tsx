import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from '../components/Navbar';
import LoginPage from "../pages/LoginPage";
import Footer from "../components/Footer";
import HomePage from "../pages/HomePage";
import Parkings from "../components/admin/parkings/Parkings";
import Floors from "../components/admin/floors/Floors";
import ParkingSpaces from "../components/admin/parkingSpaces/ParkingSpaces";
import Test from "../pages/Test";
import withGuard from "../guards/withGuard";
import ErrorPage from "../pages/ErrorPage";



const AppRouter = () => {

    const ParkingsWithGuard = withGuard(Parkings);
    const FloorsWithGuard = withGuard(Floors);
    const ParkingSpacesWithGuard = withGuard(ParkingSpaces);

    return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/parkings" element={<ParkingsWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/floors" element={<FloorsWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/parkingspaces" element={<ParkingSpacesWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/test" element={<Test />} />
        <Route path="/error" element={<ErrorPage />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default AppRouter;