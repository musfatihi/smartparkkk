import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from '../components/Navbar';
import LoginPage from "../pages/LoginPage";
import Footer from "../components/Footer";
import HomePage from "../pages/HomePage";
import Parkings from "../components/admin/parkings/Parkings";
import Floors from "../components/admin/floors/Floors";

const AppRouter = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/parkings" element={<Parkings />} />
        <Route path="/floors" element={<Floors />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default AppRouter;