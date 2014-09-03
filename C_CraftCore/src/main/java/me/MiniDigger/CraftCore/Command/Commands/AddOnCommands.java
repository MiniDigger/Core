/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.HashMap;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.CraftCore.AddOn.CoreAddOnBean;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class AddOnCommands {
	
	@Command(name = "addon", description = "Macht alles mit AddOns", usage = "", permission = "addon")
	public void addOn(CommandArgs args) {
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).command("/addon list").then("um Addons anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon info ").then("um AddonsInfos anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon install ").then("um Addons zu installieren").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon uninstall ").then("um Addons zu deinstallieren").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
	
	@Command(name = "addon.list", description = "Zeigt AddOns an", usage = "", permission = "addon.list")
	public void list(CommandArgs args) {
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).command("/addon list all").then("um alle verfügtbaren AddOns anzuzeigen")
		        .color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).command("/addon list installed").then("um alle installieten AddOns anzuzeigen")
		        .color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon list author ").then("um alle AddOns eines Autors anzuzeigen")
		        .color(ChatColor.GOLD).send(args.getSender());
	}
	
	@Command(name = "addon.list.installed", description = "Zeigt alle installierten AddOns an", usage = "", max = 0, permission = "addon.list.installed")
	public void installed(CommandArgs args) {
		
	}
	
	@Command(name = "addon.list.all", description = "Zeigt alle verfügtbaren Addons an", usage = "", max = 0, permission = "addon.list.all")
	public void all(CommandArgs args) {
		
	}
	
	@Command(name = "addon.list.author", description = "Zeigt alle verfügtbaren Addons eines Authors an", usage = "<author>", max = 1, min = 1, permission = "addon.list.author")
	public void author(CommandArgs args) {
		
	}
	
	@Command(name = "addon.info", description = "Zeigt Infos zu einem AddOn", usage = "<name>", min = 1, max = 1, permission = "addon.info")
	public void info(CommandArgs args) {
		AddOnBean bean = new CoreAddOnBean();
		bean.setName(args.getArgs()[0]);
		bean = Core.getCore().getRESTHandler().requestInfos(bean, true);
	}
	
	private HashMap<String, AddOnBean>	addOns	= new HashMap<>();
	
	@Command(name = "addon.install", description = "Installiert ein AddOn", usage = "<name> [version]", min = 1, max = 2, permission = "addon.install")
	public void install(CommandArgs args) {
		String name = args.getArgs()[0];
		AddOnBean bean = new CoreAddOnBean();
		bean.setName(name);
		bean = Core.getCore().getRESTHandler().requestInfos(bean, false);
		
		if (addOns.containsKey(args.getSender().getName())) {
			addOns.remove(args.getSender().getName());
		}
		addOns.put(args.getSender().getName(), bean);
		
		FancyMessage msg1 = Prefix.ADDON.getPrefix().then("Du bist dabei " + bean.getName() + "v" + bean.getVersion() + " von " + bean.getAuthor() + " zu installieren");
		FancyMessage msg2 = Prefix.ADDON.getPrefix().then("Drücke hier um fortzufahren,").color(ChatColor.YELLOW).command("/addon install yes")
		        .then("klicke hier um den Vorgang abzubrechen").color(ChatColor.RED).command("/addon install no");
		
		msg1.send(args.getSender());
		msg2.send(args.getSender());
	}
	
	@Command(name = "addon.install.yes", description = "", usage = "", max = 0, permission = "addon.install.yes")
	public void yes(CommandArgs args) {
		AddOnBean bean = addOns.get(args.getSender().getName());
		Core.getCore().getAddOnHandler();
		
		Prefix.ADDON.getPrefix().then("AddOn installiert! Es wird beim nächsten Neustart aktiviert!").color(ChatColor.GREEN).send(args.getSender());
	}
	
	@Command(name = "addon.install.no", description = "", usage = "", max = 0, permission = "addon.install.no")
	public void no(CommandArgs args) {
		addOns.remove(args.getSender().getName());
		
		Prefix.ADDON.getPrefix().then("Vorgang abgebrochen!").color(ChatColor.RED).send(args.getSender());
	}
	
	@Command(name = "addon.uninstall", description = "Deinstalliert ein AddOn", usage = "<name>", min = 1, max = 1, permission = "addon.uninstall")
	public void uninstall(CommandArgs args) {
		Core.getCore().getAddOnHandler().listAsUnInstalled(args.getArgs()[0]);
		Prefix.ADDON.getPrefix().then(args.getArgs()[0]+ " deinstalliert!").color(ChatColor.GREEN).send(args.getSender());
	}
	
	@Command(name = "addon.help", description = "Zeigt die Hilfe zu einem AddOn an", usage = "<name> [page]", min = 1, permission = "addon.help")
	public void help(CommandArgs args) {
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).command("/addon list").then("um Addons anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon info ").then("um AddonsInfos anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon install ").then("um Addons zu installieren").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier").color(ChatColor.YELLOW).suggest("/addon uninstall ").then("um Addons zu deinstallieren").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
}
