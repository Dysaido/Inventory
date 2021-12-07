package xyz.dysaido.inventory.button;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.dysaido.inventory.ItemBuilder;

import java.util.Objects;
import java.util.function.Consumer;

public interface Button {

    ItemBuilder getItem();

    Consumer<Player> getAction();

    static Button of(ItemBuilder stack, Consumer<Player> action) {
        Objects.requireNonNull(stack);
        Objects.requireNonNull(action);
        return new Button() {
            @Override
            public ItemBuilder getItem() {
                return stack;
            }

            @Override
            public Consumer<Player> getAction() {
                return action;
            }
        };
    }
}
