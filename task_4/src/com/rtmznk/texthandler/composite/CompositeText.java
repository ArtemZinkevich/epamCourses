package com.rtmznk.texthandler.composite;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by RTM on 01.04.2017.
 */
public class CompositeText implements TextComponent {
    private List<TextComponent> childs = new ArrayList<>();
    private TextChildLevel level;

    public void setLevel(TextChildLevel level) {
        this.level = level;
    }

    @Override
    public void add(TextComponent component) {
        childs.add(component);
    }

    public void remove(TextComponent component) {
        childs.remove(component);
    }

    @Override
    public String receiveText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : childs) {
            stringBuilder.append(component.receiveText());
        }
        return stringBuilder.toString();
    }

    @Override
    public List<TextComponent> receiveChilds() {
        List<TextComponent> allJuniorComponents = new ArrayList<>();
        for (TextComponent child : childs) {
            allJuniorComponents.add(child);
            if (child.hasChilds()) {
                allJuniorComponents.addAll(child.receiveChilds());
            }
        }
        return allJuniorComponents;
    }

    @Override
    public boolean hasChilds() {
        return childs.size() > 0;
    }

    @Override
    public TextChildLevel level() {
        return level;
    }

    public List<TextComponent> receiveComponents(TextChildLevel level) {
        List<TextComponent> allComponents = receiveChilds();
        List<TextComponent> result = new ArrayList<>();
        for (TextComponent component : allComponents) {
            if (component.level() != null && component.level().equals(level)) {
                result.add(component);
            }
        }
        return result;
    }

    @Override
    public void removeChilds() {
        childs.clear();
    }
}
