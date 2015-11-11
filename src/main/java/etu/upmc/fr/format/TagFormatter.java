package etu.upmc.fr.format;

import etu.upmc.fr.entity.Category;
import etu.upmc.fr.entity.Tag;
import etu.upmc.fr.repository.CategoryRepository;
import etu.upmc.fr.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by daniele on 08/11/15.
 */
@Service
public class TagFormatter implements Formatter<Tag> {

    @Autowired
    TagRepository tagRepository;

    public String print(Tag a, Locale locale) {
        return a.getId().toString();
    }

    public Tag parse(String id, Locale locale) throws ParseException {
        try {
            Long lid = Long.parseLong(id);
            return tagRepository.findOne(lid);
        } catch (Exception e) {
            System.err.println(id);
            e.printStackTrace();
            throw new ParseException(-1, "invalid id");
        }
    }
}
