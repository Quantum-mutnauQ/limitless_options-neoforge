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
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;


public class ValuesList extends ContainerObjectSelectionList<ValuesList.Entry> {
    String filter;
    private final ArrayList<Entry> internalChildren = new ArrayList<Entry>();
    private final Screen screen;

    public ValuesList(Minecraft p_94465_, int p_94466_, int p_94467_, int p_94468_, int p_94469_, Screen screen) {
        super(p_94465_, p_94466_, p_94467_, p_94468_, p_94469_);
        this.screen = screen;
        this.centerListVertically = false;
    }

    public void add(OptionInstance<?> p_232531_, Font font) {
        internalChildren.add(ValuesList.Entry.normal(this.minecraft.options, this.width, p_232531_, font,this.screen));
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

    protected static class Entry extends ContainerObjectSelectionList.Entry<Entry> {
        final Map<OptionInstance<?>, List<AbstractWidget>> options;
        final List<AbstractWidget> children;
        private final Screen screen;
        final String name;

        private Entry(Map<OptionInstance<?>, List<AbstractWidget>> p_169047_,String name,Screen screen) {
            this.options = p_169047_;
            this.children = p_169047_.values().stream().toList().get(0);
            this.name = name;
            this.screen=screen;
        }

        public static ValuesList.Entry normal(Options p_232538_, int p_232539_, OptionInstance<?> p_232540_, Font font,Screen screen) {
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
            stringWidget.setX(p_232539_ / 2-10-font.width(p_232540_.toString()));
            List<AbstractWidget> abstractWidgets= List.of(stringWidget,editBox);

            return new ValuesList.Entry(ImmutableMap.of(p_232540_,abstractWidgets),p_232540_.toString(),screen);
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

        public void renderContent(GuiGraphics p_440603_, int p_440287_, int p_439324_, boolean p_439478_, float p_440210_) {
            int i = 0;
            int j = this.screen.width / 2 - 155;

            this.children.forEach((p_280776_) -> {
                p_280776_.setPosition(p_280776_.getX(), this.getContentY());
                p_280776_.render(p_440603_, p_440287_, p_439324_, p_440210_);
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