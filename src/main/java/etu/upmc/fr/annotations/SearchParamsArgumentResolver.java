package etu.upmc.fr.annotations;

import etu.upmc.fr.entity.Category;
import etu.upmc.fr.entity.Tag;
import etu.upmc.fr.search.ServiceSearch;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by daniele on 11/11/15.
 */
public class SearchParamsArgumentResolver implements HandlerMethodArgumentResolver {

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
        String[] tags = webRequest.getParameterValues("tags[]");
        String categoryId = webRequest.getParameter("category");

        try {
            if(binderFactory != null) {
                WebDataBinder catBinder = binderFactory.createBinder(webRequest, (Object) null, "category");
                serviceSearch.setCategory(catBinder.convertIfNecessary(categoryId, Category.class));

                Set<Tag> tagEntities = new HashSet<>();

                WebDataBinder tagBinder = binderFactory.createBinder(webRequest, (Object) null, "tags");
                for (String tag : tags) {
                    Tag t = tagBinder.convertIfNecessary(tag, Tag.class);
                    if (t != null) tagEntities.add(t);
                }

                serviceSearch.setTags(tagEntities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serviceSearch;
    }



}
