package etu.upmc.fr.controller;

import etu.upmc.fr.entity.Tag;
import etu.upmc.fr.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by daniele on 11/11/15.
 */
@Controller
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "tag/create", method = RequestMethod.POST)
    @ResponseBody
    public Tag create (@Valid @ModelAttribute Tag tag, Errors errors) {
        if (errors.hasErrors()) {
            return tag;
        }

        Tag existing = tagRepository.findFirstByNameIgnoreCase(tag.getName());
        if (existing != null) {
            return existing;
        }

        tag = tagRepository.save(tag);

        return tag;
    }

}
