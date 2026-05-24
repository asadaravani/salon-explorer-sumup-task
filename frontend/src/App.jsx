import { Routes, Route } from "react-router-dom";
import SalonList from "./pages/SalonList";
import SalonDetails from "./pages/SalonDetails";

function App() {
    return (
        <Routes>
            <Route path="/" element={<SalonList />} />
            <Route path="/salon/:id" element={<SalonDetails />} />
        </Routes>
    );
}

export default App;