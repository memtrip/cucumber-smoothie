package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.gherkin.model.Tag;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleTag;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class TagAdapter {

    @SuppressWarnings("unchecked")
    private List<PickleTag> getPickleTagsFromPickle(Pickle pickle) {
        try {
            Field field = pickle.getClass().getDeclaredField("tags");
            field.setAccessible(true);
            return (List<PickleTag>) field.get(pickle);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // ignore
        }

        return null;
    }

    List<Tag> getTags(Pickle pickle) {

        List<Tag> tags = new ArrayList<>();
        List<PickleTag> pickleTags = getPickleTagsFromPickle(pickle);

        if (pickleTags != null) {
            for (PickleTag pickleTag : pickleTags) {
                Tag tag = new Tag();
                tag.setName(pickleTag.getName());
                tags.add(tag);
            }
        }

        return tags;
    }
}
