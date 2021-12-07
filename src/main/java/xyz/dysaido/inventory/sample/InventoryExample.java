package xyz.dysaido.inventory.sample;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dysaido.inventory.BaseInventory;
import xyz.dysaido.inventory.ItemBuilder;
import xyz.dysaido.inventory.button.Button;
import xyz.dysaido.inventory.listener.InventoryListener;
import xyz.dysaido.inventory.row.InventoryRow;

public final class InventoryExample extends JavaPlugin {
    private static final String TITLE = "&e&lTest inventoryeeeeeeeeeeeeeeeeeeeeeeee";
    private BaseInventory inventory;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.inventory = InventoryRow.SIX.createInventory(TITLE);
        this.inventory.setClickable(true);
        ItemBuilder builder = new ItemBuilder(Material.GOLDEN_APPLE, 1);
        Button button = Button.of(builder, player -> player.sendMessage("Helobelo"));
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) this.inventory.setButton(i, button);
        }
        getServer().getPluginCommand("testinv").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            this.inventory.open((Player) sender);
        }
        return true;
    }


}
