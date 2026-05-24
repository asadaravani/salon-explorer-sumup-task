package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.data.jpa.domain.Specification;

public class SalonSpecification {
    public static Specification<Salon> districtEquals(String district) {
        return (root, query, cb) ->
                district == null ? null :
                        cb.equal(root.get("address").get("district"), district);

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
