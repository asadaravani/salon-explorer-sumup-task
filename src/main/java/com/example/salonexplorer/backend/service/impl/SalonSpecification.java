package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class SalonSpecification {
    public static Specification<Salon> districtIn(List<String> districts) {
        return (root, query, cb) -> {
            if (districts == null || districts.isEmpty()) {
                return null;
            }
            return root.get("address")
                    .get("district")
                    .in(districts);
        };

    }

    public static Specification<Salon> nameOrTypeLike(String search) {
        return (root, query, cb) ->
                search == null ? null :
                        cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%");

    }

    public static Specification<Salon> ratingGreaterThan(Double minRating) {
        return (root, query, cb) ->
                minRating == null ? null :
                        cb.greaterThanOrEqualTo(root.get("rating"), minRating);

    }
}
