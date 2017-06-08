package be.feeps.epicpets.commands;

import be.feeps.epicpets.EpicPermissions;
import be.feeps.epicpets.EpicPetsPlayer;
import be.feeps.epicpets.Main;
import be.feeps.epicpets.inventories.epicpetsinv.MainInventory;
import be.feeps.epicpets.pets.DefaultPet;
import be.feeps.epicpets.utils.ItemsUtil;
import be.feeps.epicpets.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by feeps on 5/04/17.
 */
public class EpicCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){

            Player player = (Player)sender;
            EpicPetsPlayer epicPetsPlayer = EpicPetsPlayer.instanceOf(player);

            //Si il n'y a pas d'arguments
            if (args.length == 0){
                player.sendMessage(MessageUtil.translate(Main.getI().getMsgCfg().msgInvalidArgs));
                return true;

            }else if(args.length == 1){ //Sinon si il y a un argument
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage(ChatColor.DARK_GREEN + "/epicpets gui " + ChatColor.GREEN + "Open the main menu of EpicPets");
                    player.sendMessage(ChatColor.DARK_GREEN + "/epicpets spawn " + ChatColor.GREEN + "Spawn a new pet");
                    player.sendMessage(ChatColor.DARK_GREEN + "/epicpets remove " + ChatColor.GREEN + "Remove your current pet");
                    player.sendMessage(ChatColor.DARK_GREEN + "/epicpets item " + ChatColor.GREEN + "Gives you the EpicPets item");

                    return true;
                //===============================================
                } else if(args[0].equalsIgnoreCase("gui")){
                    if(EpicPermissions.OPENGUIMAIN.hasPerm(player)){
                        new MainInventory().open(player);
                    }

                    return true;
                //===============================================
                } else if (args[0].equalsIgnoreCase("spawn")) {
                    if(EpicPermissions.SPAWNPET.hasPerm(player)){
                        new DefaultPet(player);
                        //Envoye un message comme quoi le pet est bien apparu au joueur
                        player.sendMessage(MessageUtil.translate(Main.getI().getMsgCfg().msgSpawnPet));
                    }

                    return true;
                //===============================================
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if(EpicPermissions.REMOVEPET.hasPerm(player)){
                        // Remove le pet et set le pet à "NULL"
                        epicPetsPlayer.removePet();
                        player.sendMessage(MessageUtil.translate(Main.getI().getMsgCfg().msgRemovePet));
                    }
                    return true;
                //===============================================
                } else if (args[0].equalsIgnoreCase("item")) {
                    if(EpicPermissions.GIVEITEM.hasPerm(player)){
                        ItemsUtil.add(new ItemStack(Material.getMaterial(Main.getI().getMainCfg().itemOnJoin.get("material"))), player.getInventory(), Integer.parseInt(Main.getI().getMainCfg().itemOnJoin.get("slot")),
                                MessageUtil.translate(Main.getI().getMainCfg().itemOnJoin.get("name")), Arrays.asList(new String[] { "" }));
                    }
                    return true;
                    //===============================================
                }else {
                    player.sendMessage(Main.getI().getMsgCfg().msgInvalidArgs);

                    return true;
                }
            }

        }else{
            Bukkit.getServer().getLogger().severe(ChatColor.RED + MessageUtil.translate(Main.getI().getMsgCfg().msgConsoleError));
            return true;
        }
        return false;
    }
}
