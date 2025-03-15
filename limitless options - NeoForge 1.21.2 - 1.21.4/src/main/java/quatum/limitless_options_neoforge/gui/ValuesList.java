package quatum.limitless_options_neoforge.gui;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public class ValuesList extends ContainerObjectSelectionList<ValuesList.Entry> {
    String filter;
    private final ArrayList<Entry> internalChildren = new ArrayList<Entry>();

    public ValuesList(Minecraft p_94465_, int p_94466_, int p_94467_, int p_94468_, int p_94469_) {
        super(p_94465_, p_94466_, p_94467_, p_94468_, p_94469_);
        this.centerListVertically = false;
    }

    public void add(OptionInstance<?> p_232531_, Font font) {
        internalChildren.add(ValuesList.Entry.normal(this.minecraft.options, this.width, p_232531_, font));
        fillValues();
    }

    public void add(List<OptionInstance<?>> p_232534_, Font font) {
        p_232534_.forEach(optionInstance ->  this.add(optionInstance,font));

    }

    public int getRowWidth() {
        return 310;
    }


    public Optional<AbstractWidget> getMouseOver(double p_94481_, double p_94482_) {
        for(ValuesList.Entry optionslist$entry : this.children()) {
            for(AbstractWidget abstractwidget : optionslist$entry.children) {
                if (abstractwidget.isMouseOver(p_94481_, p_94482_)) {
                    return Optional.of(abstractwidget);
                }
            }
        }

        return Optional.empty();
    }

    public void updateFilter(String p_239901_) {
        this.filter = p_239901_;
        fillValues();
    }
    public void fillValues(){
        this.clearEntries();
        internalChildren.forEach(entry -> {
            if( filter == null||entry.name.toLowerCase().contains(this.filter.toLowerCase()))
                this.addEntry(entry);

        });

    }


        @OnlyIn(Dist.CLIENT)
    protected static class Entry extends ContainerObjectSelectionList.Entry<ValuesList.Entry> {
        final Map<OptionInstance<?>, List<AbstractWidget>> options;
        final List<AbstractWidget> children;
        final String name;

        private Entry(Map<OptionInstance<?>, List<AbstractWidget>> p_169047_,String name) {
            this.options = p_169047_;
            this.children = p_169047_.values().stream().toList().get(0);
            this.name = name;
        }

        public static ValuesList.Entry normal(Options p_232538_, int p_232539_, OptionInstance<?> p_232540_, Font font) {
            EditBox editBox = new EditBox(font,p_232539_ / 2,20,p_232539_/4,10, Component.empty());
            Predicate<String> charPredicate = Objects::nonNull;
            byte type;
            editBox.setMaxLength(Integer.MAX_VALUE);
            if (p_232540_.get() instanceof Integer){
                charPredicate = s -> s.matches("[0-9-]*");
                editBox.setValue(p_232540_.get().toString());
                type=1;
            } else if (p_232540_.get() instanceof Double) {
                charPredicate = s -> s.matches("[0-9.,-]*");
                editBox.setValue(BigDecimal.valueOf((Double) p_232540_.get()).toPlainString());
                type=2;
            }else if(p_232540_.get() instanceof Boolean b){
                editBox.setMaxLength(1);
                if (b == true)
                    editBox.setValue("t");
                else
                    editBox.setValue("f");
                charPredicate = s -> s.matches("[tf]*");
                type=3;
            } else {
                type = 0;
                editBox.setValue(p_232540_.get().toString());
                editBox.active=false;
            }
            editBox.setFilter(charPredicate);
            editBox.setResponder(s -> onChange(p_232540_,type,editBox,p_232538_));
            StringWidget stringWidget = new StringWidget(p_232539_ / 2,20, Component.literal(p_232540_.toString()),font);
            List<AbstractWidget> abstractWidgets= List.of(stringWidget,editBox);

            return new ValuesList.Entry(ImmutableMap.of(p_232540_,abstractWidgets),p_232540_.toString());
        }

        public static void onChange(OptionInstance<?> p_232540_,byte type, EditBox editBox,Options options){
            switch (type){
                case 1:
                    try {
                        int num = Integer.parseInt(editBox.getValue());
                        ((OptionInstance<Integer>) p_232540_).set(num);
                    } catch (NumberFormatException e) {
                    }
                    break;
                case 2:
                    try {
                        BigDecimal number = new BigDecimal(editBox.getValue());
                        ((OptionInstance<Double>) p_232540_).set(number.doubleValue());

                    } catch (NumberFormatException e) {
                    }
                    break;
                case 3:
                    var option = (OptionInstance<Boolean>) p_232540_;
                    if(editBox.getValue().equals("t"))
                        option.set(true);
                    else if(editBox.getValue().equals("f"))
                        option.set(false);
                    break;
                default:

                    break;
            }

        }

        public void render(GuiGraphics p_281311_, int p_94497_, int p_94498_, int p_94499_, int p_94500_, int p_94501_, int p_94502_, int p_94503_, boolean p_94504_, float p_94505_) {
            this.children.forEach((p_280776_) -> {
                p_280776_.setY(p_94498_);
                p_280776_.render(p_281311_, p_94502_, p_94503_, p_94505_);
            });
        }

        public List<? extends GuiEventListener> children() {
            return this.children;
        }

        public List<? extends NarratableEntry> narratables() {
            return this.children;
        }
    }
}