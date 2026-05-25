import { Routes, Route } from "react-router-dom";
import SalonList from "./pages/SalonList";
import SalonDetails from "./pages/SalonDetails";
import SalonUpdate from "./pages/SalonUpdate.jsx";

function App() {
    return (
        <Routes>
            <Route path="/" element={<SalonList />} />
            <Route path="/salon/:id" element={<SalonDetails />} />
            <Route path="/salon/:id/edit" element={<SalonUpdate />} />
        </Routes>
    );
}

export default App;