import { useEffect, useState } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import "../styles/SalonUpdate.css";

function SalonUpdate() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [salon, setSalon] = useState(null);

    const [formData, setFormData] = useState({
        name: "",
        district: "",
        phoneNumber: "",
        websiteUrl: "",
        googleMapsUrl: "",
        userRatingCount: 0,
        rating: 0.0,
        types: [],
        photosUrlsToRemove: []
    });


    const [typesString, setTypesString] = useState("");

    useEffect(() => {
        fetch(`http://localhost:8080/api/salons/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setSalon(data);
                setFormData({
                    name: data.name || "",
                    district: data.address?.district || "",
                    phoneNumber: data.phoneNumber || "",
                    websiteUrl: data.websiteUrl || "",
                    googleMapsUrl: data.googleMapsUrl || "",
                    userRatingCount: data.userRatingCount || 0,
                    rating: data.rating || 0.0,
                    types: data.types_services || [],
                    photosUrlsToRemove: []
                });
                setTypesString((data.types_services || []).join(", "));
            })
            .catch((err) => console.error("Error fetching salon:", err));
    }, [id]);

    const handleChange = (e) => {
        const { name, value, type } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: type === "number" ? Number(value) : value,
        }));
    };

    const handleTypesChange = (e) => {
        setTypesString(e.target.value);
        const typesArray = e.target.value
            .split(",")
            .map((t) => t.trim())
            .filter((t) => t !== "");

        setFormData((prev) => ({
            ...prev,
            types: typesArray,
        }));
    };

    const handlePhotoToggle = (photoPath) => {
        setFormData((prev) => {
            const isMarked = prev.photosUrlsToRemove.includes(photoPath);
            const updatedPhotos = isMarked
                ? prev.photosUrlsToRemove.filter((url) => url !== photoPath)
                : [...prev.photosUrlsToRemove, photoPath];

            return {
                ...prev,
                photosUrlsToRemove: updatedPhotos,
            };
        });
    };

    const handleSave = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/salons/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                navigate(`/salon/${id}`); // Redirect back to details page on success
            } else {
                console.error("Failed to update salon.");
            }
        } catch (error) {
            console.error("Error during update:", error);
        }
    };

    if (!salon) return <div className="loading">Loading...</div>;

    const mainPhoto = salon.photos?.[0]?.path || "https://i.imgur.com/4M34hi2.png";

    return (
        <div className="profile-page edit-mode">
            <Link to={`/salon/${id}`} className="home-btn cancel-btn">
                Cancel
            </Link>
            <button onClick={handleSave} className="edit-btn save-btn">
                Save
            </button>

            {/* HEADER */}
            <div className="profile-header">
                <img className="profile-avatar" src={mainPhoto} alt="Salon Avatar" />

                <div className="profile-info">
                    <input
                        className="edit-input edit-h1"
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        placeholder="Salon Name"
                    />

                    <div className="profile-stats">
                        <div className="stat-item">
                            <b>⭐</b>
                            <input
                                className="edit-input edit-number"
                                type="number"
                                step="0.1"
                                name="rating"
                                value={formData.rating}
                                onChange={handleChange}
                            />
                            rating
                        </div>
                        <div className="stat-item">
                            <input
                                className="edit-input edit-number"
                                type="number"
                                name="userRatingCount"
                                value={formData.userRatingCount}
                                onChange={handleChange}
                            />
                            reviews
                        </div>
                        <div className="stat-item">
                            📍
                            <input
                                className="edit-input edit-text"
                                type="text"
                                name="district"
                                value={formData.district}
                                onChange={handleChange}
                                placeholder="District"
                            />
                        </div>
                    </div>

                    <div className="profile-actions edit-actions-grid">
                        <label>
                            <span>Phone:</span>
                            <input
                                className="edit-input"
                                type="text"
                                name="phoneNumber"
                                value={formData.phoneNumber}
                                onChange={handleChange}
                                placeholder="Phone Number"
                            />
                        </label>
                        <label>
                            <span>Maps URL:</span>
                            <input
                                className="edit-input"
                                type="text"
                                name="googleMapsUrl"
                                value={formData.googleMapsUrl}
                                onChange={handleChange}
                                placeholder="https://maps.google.com/..."
                            />
                        </label>
                        <label>
                            <span>Website:</span>
                            <input
                                className="edit-input"
                                type="text"
                                name="websiteUrl"
                                value={formData.websiteUrl}
                                onChange={handleChange}
                                placeholder="https://..."
                            />
                        </label>
                    </div>
                </div>
            </div>

            {/* SERVICES / TAGS */}
            <div className="tags-edit-section">
                <label className="tags-label">Services / Types (Comma Separated):</label>
                <input
                    className="edit-input edit-tags"
                    type="text"
                    value={typesString}
                    onChange={handleTypesChange}
                    placeholder="e.g., HAIRCUT, COLORING, NAILS"
                />
            </div>

            <div className="photo-feed">
                {salon.photos?.slice(0, 9).map((p, i) => {
                    const isMarked = formData.photosUrlsToRemove.includes(p.path);
                    return (
                        <div
                            key={i}
                            className={`photo-item ${isMarked ? "marked" : ""}`}
                            onClick={() => handlePhotoToggle(p.path)}
                        >
                            <img src={p.path} alt={`Gallery ${i}`} />
                            {isMarked && <div className="photo-overlay">✕</div>}
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default SalonUpdate;