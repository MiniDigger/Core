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
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

import me.MiniDigger.CraftCore.AddOn.CoreAddOnBean;

import mkremins.fanciful.FancyMessage;

public class AddOnCommands {

	@Command(name = "addon", description = "Macht alles mit AddOns", usage = "", permission = "addon")
	public void addOn(final CommandArgs args) {
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/addon list").then("um Addons anzuzeigen").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon info ").then("um AddonsInfos anzuzeigen").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon install ").then("um Addons zu installieren").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/addon install all").then("um ALLE Addons zu installieren").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon uninstall ").then("um Addons zu deinstallieren").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon update  ").then("um Addons zu aktualisieren").color(ChatColor.GOLD).send(args.getSender());
	}

	@Command(name = "addon.list", description = "Zeigt AddOns an", usage = "", permission = "addon.list")
	public void list(final CommandArgs args) {
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/addon list all").then("um alle verfügtbaren AddOns anzuzeigen").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/addon list installed").then("um alle installieten AddOns anzuzeigen").color(ChatColor.GOLD).send(args.getSender());
		Prefix.ADDON.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon list author ").then("um alle AddOns eines Autors anzuzeigen").color(ChatColor.GOLD).send(args.getSender());
	}

	@Command(name = "addon.list.installed", description = "Zeigt alle installierten AddOns an", usage = "", max = 0, permission = "addon.list.installed")
	public void installed(final CommandArgs args) {
		Prefix.ADDON.getPrefix().then("======== Installierte AddOns =========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());

		for (final AddOnBean bean : Core.getCore().getAddOnHandler().getInstalledBeans()) {
			Prefix.ADDON.getPrefix().then(bean.getName() + " v" + bean.getVersion()).color(ChatColor.YELLOW).tooltip("Klicke zum Deinstallieren").suggest("/addon uninstall " + bean.getName())
					.then(" von " + bean.getAuthor()).tooltip("Klicke um alle", "Addons des Autors", "zu sehen").command("/addon list author " + bean.getAuthor()).color(ChatColor.GOLD).send(args.getSender());
		}

		Prefix.ADDON.getPrefix().then("======== =================== =========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());
	}

	@Command(name = "addon.list.all", description = "Zeigt alle verfügtbaren Addons an", usage = "", max = 0, permission = "addon.list.all")
	public void all(final CommandArgs args) {
		Prefix.ADDON.getPrefix().then("======== Verfügbare AddOns =========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());

		for (final AddOnBean bean : Core.getCore().getRESTHandler().getAllAddOns()) {
			Prefix.ADDON.getPrefix().then(bean.getName() + " v" + bean.getVersion()).color(ChatColor.YELLOW).tooltip("Klicke zum Installieren").suggest("/addon install " + bean.getName() + " " + bean.getVersion())
					.then(" von " + bean.getAuthor()).tooltip("Klicke um alle", "Addons des Autors", "zu sehen").command("/addon list author " + bean.getAuthor()).color(ChatColor.GOLD).send(args.getSender());
		}

		Prefix.ADDON.getPrefix().then("======== ================= =========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());
	}

	@Command(name = "addon.list.author", description = "Zeigt alle verfügtbaren Addons eines Authors an", usage = "<author>", max = 1, min = 1, permission = "addon.list.author")
	public void author(final CommandArgs args) {
		Prefix.ADDON.getPrefix().then("======== AddOns von " + args.getArgs()[0] + "=========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());

		for (final AddOnBean bean : Core.getCore().getRESTHandler().getAllAddOnsBy(args.getArgs()[0])) {
			Prefix.ADDON.getPrefix().then(bean.getName() + " v" + bean.getVersion()).color(ChatColor.YELLOW).tooltip("Klicke zum Installieren").suggest("/addon install " + bean.getName()).then(" von " + bean.getAuthor())
					.tooltip("Klicke um alle", "Addons des Autors", "zu sehen").command("/addon list author " + bean.getAuthor()).color(ChatColor.GOLD).send(args.getSender());

		}

		Prefix.ADDON.getPrefix().then("======== ================= =========").color(ChatColor.GOLD).style(ChatColor.BOLD).send(args.getSender());
	}

	@Command(name = "addon.info", description = "Zeigt Infos zu einem AddOn", usage = "<name>", min = 1, max = 1, permission = "addon.info")
	public void info(final CommandArgs args) {
		AddOnBean bean = new CoreAddOnBean();
		bean.setName(args.getArgs()[0]);
		bean = Core.getCore().getRESTHandler().requestInfos(bean, true);
		final FancyMessage msg1 = Prefix.ADDON.getPrefix().then("Info: " + bean.getName() + " v" + bean.getVersion() + " von " + bean.getAuthor());
		msg1.send(args.getSender());
	}

	private final HashMap<String, List<AddOnBean>> addOns = new HashMap<>();

	@Command(name = "addon.install", description = "Installiert ein AddOn", usage = "<name> [version]", min = 1, max = 2, permission = "addon.install")
	public void install(final CommandArgs args) {
		final String name = args.getArgs()[0];
		String version = null;

		if (name.equalsIgnoreCase("all")) {
			addOns.put(args.getSender().getName(), Core.getCore().getRESTHandler().getAllAddOns());
			final FancyMessage msg1 = Prefix.ADDON.getPrefix().then("Du bist dabei ALLLE Addons zu installieren");
			final FancyMessage msg2 = Prefix.ADDON.getPrefix().then("Drücke hier um fortzufahren,").color(ChatColor.YELLOW).command("/addon install yes").then("klicke hier um den Vorgang abzubrechen")
					.color(ChatColor.RED).command("/addon install no");

			msg1.send(args.getSender());
			msg2.send(args.getSender());
			return;
		}

		AddOnBean bean = new CoreAddOnBean();
		bean.setName(name);

		if (args.getArgs().length == 2) {
			version = args.getArgs()[1];
			bean.setVersion(version);
		}

		bean = Core.getCore().getRESTHandler().requestInfos(bean, version == null ? false : true);

		if (bean.getVersion() == null || bean.getAuthor() == null) {
			Prefix.ADDON.getPrefix().then("AddOn wurde nicht gefunden!").color(ChatColor.RED).send(args.getSender());
			Prefix.ADDON.getPrefix().then("Benutze /addon list all um alle AddOns anzuzeigen").color(ChatColor.YELLOW).send(args.getSender());
			return;
		}

		if (addOns.containsKey(args.getSender().getName())) {
			addOns.remove(args.getSender().getName());
		}
		final List<AddOnBean> beans = new ArrayList<AddOnBean>();
		beans.add(bean);
		addOns.put(args.getSender().getName(), beans);

		final FancyMessage msg1 = Prefix.ADDON.getPrefix().then("Du bist dabei " + bean.getName() + " v" + bean.getVersion() + " von " + bean.getAuthor() + " zu installieren");
		final FancyMessage msg2 = Prefix.ADDON.getPrefix().then("Drücke hier um fortzufahren,").color(ChatColor.YELLOW).command("/addon install yes").then("klicke hier um den Vorgang abzubrechen").color(ChatColor.RED)
				.command("/addon install no");

		msg1.send(args.getSender());
		msg2.send(args.getSender());
	}

	@Command(name = "addon.install.yes", description = "", usage = "", max = 0, permission = "addon.install.yes")
	public void yes(final CommandArgs args) {
		for (final AddOnBean bean : addOns.get(args.getSender().getName())) {
			addOns.remove(args.getSender().getName());
			Core.getCore().getAddOnHandler().listAsInstalled(bean);
		}
		Prefix.ADDON.getPrefix().then("AddOn installiert! Es wird beim nächsten Neustart aktiviert!").color(ChatColor.GREEN).send(args.getSender());
	}

	@Command(name = "addon.install.no", description = "", usage = "", max = 0, permission = "addon.install.no")
	public void no(final CommandArgs args) {
		addOns.remove(args.getSender().getName());

		Prefix.ADDON.getPrefix().then("Vorgang abgebrochen!").color(ChatColor.RED).send(args.getSender());
	}

	@Command(name = "addon.uninstall", description = "Deinstalliert ein AddOn", usage = "<name>", min = 1, max = 1, permission = "addon.uninstall")
	public void uninstall(final CommandArgs args) {
		Core.getCore().getAddOnHandler().listAsUnInstalled(args.getArgs()[0]);
		Prefix.ADDON.getPrefix().then(args.getArgs()[0] + " deinstalliert!").color(ChatColor.GREEN).send(args.getSender());
	}

	@Command(name = "addon.update", description = "Aktualisiert ein AddOn", usage = "<name>", min = 1, max = 1, permission = "addon.update")
	public void update(final CommandArgs args) {
		if (Core.getCore().getAddOnHandler().update(args.getArgs()[0])) {
			Prefix.ADDON.getPrefix().then(args.getArgs()[0] + " aktualisiert!").color(ChatColor.GREEN).send(args.getSender());
		} else {
			Prefix.ADDON.getPrefix().then(args.getArgs()[0] + " wurde nicht aktualisiert!").color(ChatColor.RED).send(args.getSender());
			Prefix.ADDON.getPrefix().then("War es schon aktuell?").color(ChatColor.YELLOW).send(args.getSender());
		}
	}
}
