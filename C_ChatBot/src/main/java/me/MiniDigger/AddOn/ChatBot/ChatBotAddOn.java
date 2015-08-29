package me.MiniDigger.AddOn.ChatBot;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.AddOn.CoreAddOn;
import me.MiniDigger.CraftCore.Event.Events.CoreUserChatEvent;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.User.CoreUser;

public class ChatBotAddOn extends CoreAddOn {

	private jMegaHalHandler	bot1;
	private MegaHalHandler	bot2;

	private final String[]				triggers	= new String[] { "pinkbot" };
	private final BlockingQueue<String>	input		= new LinkedBlockingQueue<String>();

	private User	user1;
	private User	user2;

	@Override
	public void enable() {

		new BukkitRunnable() {

			@Override
			public void run() {
				user1 = new CoreUser(null);
				user2 = new CoreUser(null);

				user1.setDisplayName("PinkBot1");
				user2.setDisplayName("PinkBot2");

				user1.setPrefix("");
				user2.setPrefix("");

				user1.setSuffix("");
				user2.setSuffix("");

				user1.setPrimaryChannel(Core.getCore().getGameHandler().getMainGame().getChatChannel());
				user2.setPrimaryChannel(Core.getCore().getGameHandler().getMainGame().getChatChannel());

				bot1 = new jMegaHalHandler(ChatBotAddOn.this);
				bot2 = new MegaHalHandler(ChatBotAddOn.this);

				try {
					bot1.load();
				}
				catch (ClassNotFoundException | IOException e) {
					MSG.stacktrace(LogLevel.DEBUG, e);
				}

				bot2.load();

				final Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						while (true) {
							try {
								final String input = ChatBotAddOn.this.input.take();
								for (final String trigger : triggers) {
									if (input.matches("^.*(?i)" + trigger + ".*$")) {
										try {
											final String sentence1 = getSentenceBot1(trigger, input);
											final String sentence2 = getSentenceBot2(trigger, input);

											Core.getCore().getChatHandler().handleChat(user1, sentence1);
											Core.getCore().getChatHandler().handleChat(user2, sentence2);
										}
										catch (final Exception ex) {
											MSG.stacktrace(LogLevel.DEBUG, ex);
										}
									}
								}
							}
							catch (final InterruptedException e) {
								MSG.stacktrace(LogLevel.DEBUG, e);
							}
						}
					}

					private synchronized String getSentenceBot1(final String trigger, final String message) {
						String sentence = bot1.reply(message);
						while (sentence.matches("^.*(?i)" + trigger + ".*$")) {
							sentence = bot1.reply(message.replaceAll("(?i)" + trigger, "").split(" ")[Core.getCore().getRandomUtil().nextInt(message.split(" ").length)]);
						}
						return sentence;
					}

					private synchronized String getSentenceBot2(final String trigger, final String message) {
						String sentence = bot2.reply(message);
						while (sentence.matches("^.*(?i)" + trigger + ".*$")) {
							sentence = bot2.reply(message.replaceAll("(?i)" + trigger, "").split(" ")[Core.getCore().getRandomUtil().nextInt(message.split(" ").length)]);
						}
						return sentence;
					}

				});
				t.setName("BotHandler");
				t.start();
			}
		}.runTaskLater(Core.getCore().getInstance(), 20);
		super.enable();
	}

	@Override
	public void disable() {
		try {
			bot1.save();
		}
		catch (final IOException e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}

		bot2.save();
		super.disable();
	}

	private String fix(String input) {
		final String[] old = new String[] { "ä", "ö", "ü", "Ä", "Ö", "Ü", "ß" };
		final String[] neu = new String[] { "ae", "oe", "ue", "AE", "OE", "UE", "SS" };

		for (int i = 0; i < old.length; i++) {
			input = input.replaceAll(old[i], neu[i]);
		}
		return input;
	}

	public String clean(String string) {
		if (string != null && string.length() > 300) {
			string = string.substring(0, 300);
		}
		final String newstring = string.replaceAll("<.*?>", "").replaceAll("\\[.*?\\]", "");
		return newstring;
	}

	@EventHandler
	public void onChat(final CoreUserChatEvent e) {
		String msg = ChatColor.stripColor(e.getMsg());
		msg = fix(msg);

		if (e.getUser().hasPermission("bot.log")) {
			final String clean = clean(msg);
			bot1.train(clean);
			bot2.train(clean);
		}
		if (e.getUser().hasPermission("bot.trigger")) {
			boolean cancel = false;
			for (final String trigger : triggers) {
				if (msg.matches("^.*(?i)" + trigger + ".*$")) {
					cancel = true;
					break;
				}
			}
			if (!cancel) {
				final String clean = clean(msg);
				bot1.train(clean);
				bot2.train(clean);
			} else {
				input.add(msg);
			}
		}
	}

}
