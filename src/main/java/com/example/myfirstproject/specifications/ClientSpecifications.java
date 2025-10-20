package com.example.myfirstproject.specifications;

import com.example.myfirstproject.model.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecifications {

    /**
     * SPECIFICATION 1 : Recherche par prénom exact
     * Exemple : WHERE firstname = 'Jean'
     */
    public static Specification<Client> hasFirstname(String firstname) {
        return (root, query, criteriaBuilder) -> {
            // Si le prénom n'est pas renseigné, on ignore cette condition
            if (firstname == null || firstname.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // Condition toujours vraie
            }

            // Crée la condition : firstname = valeur
            return criteriaBuilder.equal(root.get("firstname"), firstname);
        };
    }

    /**
     * SPECIFICATION 2 : Recherche par nom de famille exact
     * Exemple : WHERE lastname = 'Dupont'
     */
    public static Specification<Client> hasLastname(String lastname) {
        return (root, query, criteriaBuilder) -> {
            if (lastname == null || lastname.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("lastname"), lastname);
        };
    }

    /**
     * SPECIFICATION 3 : Clients majeurs (âge >= 18)
     * Exemple : WHERE age >= 18
     */
    public static Specification<Client> isAdult() {
        return (root, query, criteriaBuilder) -> {
            // greaterThanOrEqualTo signifie "supérieur ou égal"
            return criteriaBuilder.greaterThanOrEqualTo(root.get("age"), 18);
        };
    }
}