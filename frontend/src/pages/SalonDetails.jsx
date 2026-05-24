import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../styles/SalonDetails.css";
import { Link } from "react-router-dom";

function SalonDetails() {
    const { id } = useParams();
    const [salon, setSalon] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/salons/${id}`)
            .then(res => res.json())
            .then(setSalon);
    }, [id]);

    if (!salon) return <div className="loading">Loading...</div>;

    const mainPhoto =
        salon.photos?.[0]?.path ||
        "https://i.imgur.com/4M34hi2.png";

    return (
        <div className="profile-page">
            <Link to="/" className="home-btn">
                ←Back to Home
            </Link>
            <Link to="/" className="edit-btn">
                Edit
            </Link>
            {/* HEADER */}
            <div className="profile-header">

                <img className="profile-avatar" src={mainPhoto} alt="" />

                <div className="profile-info">
                    <h1>{salon.name}</h1>

                    <div className="profile-stats">
                        <div><b>⭐ {salon.rating}</b> rating</div>
                        <div>{salon.userRatingCount} reviews</div>
                        <div>📍 {salon.address.district}</div>
                    </div>

                    <div className="profile-actions">
                        <a href={salon.googleMapsUrl} target="_blank">
                            Open in Maps
                        </a>
                        {salon.websiteUrl && (
                            <a href={salon.websiteUrl} target="_blank">
                                Website
                            </a>
                        )}
                    </div>
                </div>
            </div>

            {/* SERVICES / TAGS */}
            <div className="tags">
                {salon.types_services?.map((t) => (
                    <span key={t} className="tag">
                        {t.replaceAll("_", " ")}
                    </span>
                ))}
            </div>

            {/* PHOTO FEED */}
            <div className="photo-feed">
                {salon.photos?.slice(0, 9).map((p, i) => (
                    <img key={i} src={p.path} alt="" />
                ))}
            </div>

        </div>
    );
}

export default SalonDetails;