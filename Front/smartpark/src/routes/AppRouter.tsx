import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from "../pages/LoginPage";
import Footer from "../components/Footer";
import HomePage from "../pages/HomePage";
import Parkings from "../components/admin/parkings/Parkings";
import Floors from "../components/admin/floors/Floors";
import ParkingSpaces from "../components/admin/parkingSpaces/ParkingSpaces";
import withGuard from "../guards/withGuard";
import ErrorPage from "../pages/ErrorPage";
import ParkingsList from "../components/admin/ParkingsList";
import BookingForm from "../components/client/bookings/BookingForm";
import MyBookings from "../components/client/bookings/bookings";
import Navbar from "../components/Navbar";



const AppRouter = () => {

    const HomePageWithGuard = withGuard(HomePage)

    const ParkingsWithGuard = withGuard(Parkings);
    const FloorsWithGuard = withGuard(Floors);
    const ParkingSpacesWithGuard = withGuard(ParkingSpaces);

    const ParkingsListWithGuard = withGuard(ParkingsList);
    const MyBookingsWithGuard = withGuard(MyBookings);
    const BookingFormWithGuard = withGuard(BookingForm);

    return (
    <Router>
        <Navbar />
      <Routes>
         <Route path="/" element={<HomePage />} />
        <Route path="/client/bookings" element={<MyBookingsWithGuard allowedRoles={['ROLE_CLIENT']}/>}/>
        <Route path="/admin/parkings" element={<ParkingsWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/client/parkings" element={<ParkingsListWithGuard allowedRoles={['ROLE_CLIENT']}/>} />
        <Route path="/admin/floors" element={<FloorsWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/admin/parkingspaces" element={<ParkingSpacesWithGuard allowedRoles={['ROLE_ADMIN']}/>} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/client/book" element={<BookingFormWithGuard allowedRoles={['ROLE_CLIENT']}/>} />
        <Route path="/error" element={<ErrorPage />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default AppRouter;