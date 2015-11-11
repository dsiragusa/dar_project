package etu.upmc.fr.search;

import etu.upmc.fr.entity.Category;
import etu.upmc.fr.entity.Tag;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by daniele on 11/11/15.
 */
public class ServiceSearch {

    private Set<Tag> tags;
    private Category category;
    private String title;

    public ServiceSearch() {
        this.tags = new HashSet<>();
    }

    public static String tagsKey = "tags";

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public static String categoryKey = "category";

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static String titleKey = "title";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("&");
        boolean empty = true;

        if (StringUtils.hasText(title)) {
            empty = false;
            s.append(titleKey).append("=").append(title);
        }

        if (category != null) {
            if ( ! empty) {
                s.append("&");
            }
            empty = false;

            s.append(categoryKey).append("=").append(category.getId());
        }

        if ( ! tags.isEmpty()) {
            for (Tag t : tags) {
                if ( ! empty) {
                    s.append("&");
                }
                empty = false;

                s.append(tagsKey).append("[]=").append(t.getId());
            }
        }

        return empty ? "" : s.toString();
    }
}
