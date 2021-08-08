package xyz.dysaido.inventory;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class InventoryExample extends JavaPlugin {
    private static final String TITLE = "Test inventory";
    DyInventoryManager dyInventoryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        dyInventoryManager  = new DyInventoryManager(this);
        DyInventory dyInventory = new DyInventory(TITLE, 3);
        dyInventory.setClickable(true);
        CustomAction<ItemStack, Player> action = new CustomAction<>(DyItemBuilder.create(Material.ANVIL, 5, "hello"));
        action.addAction(player -> player.kickPlayer("SIKERULT ELBASZNOD VALAMIT"));
        dyInventory.addItem(0, action);
        dyInventory.addItem(3, action);
        dyInventory.addItem(6, action);
        dyInventory.addItem(9, new CustomAction<>(DyItemBuilder.create(Material.ANVIL, 8, "buzi")));
        dyInventory.addItem(12, new CustomAction<>(DyItemBuilder.create(Material.ANVIL, 2, "marci")));
        dyInventory.addItem(15, new CustomAction<>(DyItemBuilder.create(Material.ANVIL, 59, "pan")));
        dyInventoryManager.addInventory(dyInventory);
        getServer().getPluginCommand("testinv").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Optional<DyInventory> optional = dyInventoryManager.findByTitle(TITLE);
            optional.ifPresent(dyInventory -> dyInventory.open((Player) sender));
        }
        return true;
    }


}
