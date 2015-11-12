package etu.upmc.fr.annotations;

import etu.upmc.fr.entity.Category;
import etu.upmc.fr.entity.Tag;
import etu.upmc.fr.search.ServiceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by daniele on 11/11/15.
 */
public class SearchParamsArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    DomainClassConverter<?> domainClassConverter;

    private static final TypeDescriptor STRING_TD = TypeDescriptor.valueOf(String.class);
    private static final TypeDescriptor TAG_TD = TypeDescriptor.valueOf(Tag.class);

    public SearchParamsArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return ServiceSearch.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ServiceSearch serviceSearch = new ServiceSearch();

        serviceSearch.setTitle(webRequest.getParameter("title"));
        String[] tags = webRequest.getParameterValues("tags");
        String categoryId = webRequest.getParameter("category");

        try {
            if(binderFactory != null) {
                WebDataBinder catBinder = binderFactory.createBinder(webRequest, (Object) null, "category");
                serviceSearch.setCategory(catBinder.convertIfNecessary(categoryId, Category.class));

                if (tags != null) {
                    Set<Tag> tagEntities = new HashSet<>();

                    for (String tag : tags) {
                        Tag t = (Tag) domainClassConverter.convert(tag, STRING_TD, TAG_TD);
                        tagEntities.add(t);
                    }

                    serviceSearch.setTags(tagEntities);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serviceSearch;
    }



}
