import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "../styles/SalonList.css";


function SalonList() {
    const [salons, setSalons] = useState([]);
    const [districts, setDistricts] = useState([]);
    const navigate = useNavigate();
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [search, setSearch] = useState("");
    const [minRating, setMinRating] = useState(4.5);

    const [selectedDistricts, setSelectedDistricts] = useState([]);

    // NEW: State for toggling the filter dropdown
    const [showFilters, setShowFilters] = useState(false);

    const districtParam =
        selectedDistricts.length > 0
            ? selectedDistricts
                .map(d => `districts=${encodeURIComponent(d)}`)
                .join("&")
            : "";

    useEffect(() => {
        fetch("http://localhost:8080/api/salons/districts")
            .then((res) => res.json())
            .then(setDistricts)
            .catch(console.error);
    }, []);

    useEffect(() => {
        fetch(
            `http://localhost:8080/api/salons?page=${page}&size=20&${districtParam}&search=${search}&minRating=${minRating}`
        )
            .then((res) => res.json())
            .then((data) => {
                setSalons(data.content || []);
                setTotalPages(data.totalPages);
            })
            .catch(console.error);
    }, [page, districtParam, search, minRating]);

    return (
        <div className="container">
            {/* NEW: Sticky Top Bar */}
            <div className="top-bar">
                <input
                    className="search-input"
                    type="text"
                    placeholder="Search for a salon..."
                    value={search}
                    onChange={(e) => {
                        setPage(0);
                        setSearch(e.target.value);
                    }}
                />

                <div className="filter-wrapper">
                    <button
                        className="filter-button"
                        onClick={() => setShowFilters(!showFilters)}
                    >
                        {/* Filter Icon */}
                        <svg width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M1.5 1.5A.5.5 0 0 1 2 1h12a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.128.334L10 8.692V13.5a.5.5 0 0 1-.342.474l-3 1A.5.5 0 0 1 6 14.5V8.692L1.628 3.834A.5.5 0 0 1 1.5 3.5v-2zm1 .5v1.308l4.372 4.834A.5.5 0 0 1 7 8.5v5.306l2-.666V8.5a.5.5 0 0 1 .128-.334L13.5 3.308V2h-11z"/>
                        </svg>
                        Filters {selectedDistricts.length > 0 && <span className="active-dot" />}
                    </button>

                    {showFilters && (
                        <div className="filter-dropdown">
                            <div className="filter-group">
                                <label>Minimum rating ({minRating} ⭐)</label>
                                <input
                                    className="rating-slider"
                                    type="range"
                                    step="0.1"
                                    min="0"
                                    max="5"
                                    value={minRating}
                                    onChange={(e) => {
                                        setPage(0);
                                        setMinRating(Number(e.target.value));
                                    }}
                                />
                            </div>

                            <div className="filter-group">
                                <label>Districts</label>
                                <div className="districts-list">
                                    {districts.map((district) => (
                                        <label key={district.name} className="district-item">
                                            <input
                                                type="checkbox"
                                                checked={selectedDistricts.includes(district.name)}
                                                onChange={() => {
                                                    setPage(0);
                                                    setSelectedDistricts(prev =>
                                                        prev.includes(district.name)
                                                            ? prev.filter(d => d !== district.name)
                                                            : [...prev, district.name]
                                                    );
                                                }}
                                            />
                                            <span>{district.name}</span>
                                            <span className="count">
                                                ({district.availableSalonsCount})
                                            </span>
                                        </label>
                                    ))}
                                </div>
                                {selectedDistricts.length > 0 && (
                                    <button className="clear-btn" onClick={() => setSelectedDistricts([])}>
                                        Clear Districts
                                    </button>
                                )}
                            </div>
                        </div>
                    )}
                </div>
            </div>

            <div className="content">
                <h1 className="title">Warsaw Salon Explorer</h1>

                <div className="grid">
                    {salons.map((salon) => (
                        <div className="card" key={salon.id}
                             onClick={() => navigate(`/salon/${salon.id}`)}>
                            <img
                                src={salon.photoUrl || "https://i.imgur.com/Z2MYNbj.png"}
                                alt={salon.name}
                                onError={(e) => {
                                    e.target.src = "https://i.imgur.com/4M34hi2.png";
                                }}
                            />
                            {/* BADGE */}
                            {(salon.rating >= 4.9) && (
                                <div className="badge top-rated">
                                    Top rated
                                </div>
                            )}
                            <div className="card-overlay">
                            </div>
                            <div className="card-content">
                                <h2>{salon.name}</h2>
                                <p className="district">📍 {salon.district}</p>
                                <p className="rating">⭐ {salon.rating}</p>
                            </div>
                        </div>
                    ))}
                </div>

                <div className="pagination">
                    <button
                        disabled={page === 0}
                        onClick={() => setPage(page - 1)}
                    >
                        ← Previous
                    </button>

                    <span>
                        Page {page + 1} / {totalPages}
                    </span>

                    <button
                        disabled={page + 1 >= totalPages}
                        onClick={() => setPage(page + 1)}
                    >
                        Next →
                    </button>
                </div>
            </div>
        </div>
    );
}

export default SalonList;