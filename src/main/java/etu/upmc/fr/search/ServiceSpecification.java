package etu.upmc.fr.search;

import etu.upmc.fr.entity.Service;
import etu.upmc.fr.entity.Tag;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daniele on 11/11/15.
 */
public class ServiceSpecification implements Specification<Service> {
    private ServiceSearch serviceSearch;

    public ServiceSpecification(ServiceSearch serviceSearch) {
        this.serviceSearch = serviceSearch;
    }

    @Override
    public Predicate toPredicate(Root<Service> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicateList = new ArrayList<>();

        if ( ! StringUtils.isEmpty(serviceSearch.getTitle())) {
            Path<String> path = root.get(ServiceSearch.titleKey);
            predicateList.add(criteriaBuilder.like(path, '%' + serviceSearch.getTitle() + '%'));
        }

        if (serviceSearch.getCategory() != null) {
            predicateList.add(criteriaBuilder.equal(root.get(ServiceSearch.categoryKey), serviceSearch.getCategory()));
        }

        List<Predicate> tagPreds = new ArrayList<>();
        Path<Set<Tag>> path = root.get(ServiceSearch.tagsKey);
        for (Tag t : serviceSearch.getTags()) {
            tagPreds.add(criteriaBuilder.isMember(t, path));
        }
        if ( ! tagPreds.isEmpty()) {
            predicateList.add(criteriaBuilder.or(tagPreds.toArray(new Predicate[tagPreds.size()])));
        }

        if (serviceSearch.getToExclude() != null) {
            predicateList.add(criteriaBuilder.notEqual(root.get(ServiceSearch.accountExcludeKey), serviceSearch.getToExclude()));
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
