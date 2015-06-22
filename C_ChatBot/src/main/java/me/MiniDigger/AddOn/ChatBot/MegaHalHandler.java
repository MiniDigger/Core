package me.MiniDigger.AddOn.ChatBot;

import java.io.IOException;

import org.trypticon.megahal.engine.MegaHAL;

import me.MiniDigger.Core.Lang.LogLevel;

import me.MiniDigger.CraftCore.Lang._;

public class MegaHalHandler {
	
	private MegaHAL	     brain;
	private ChatBotAddOn	addon;
	
	public MegaHalHandler(ChatBotAddOn addon) {
		this.addon = addon;
	}
	
	public void save() {
		
	}
	
	public void load() {
		try {
			brain = new MegaHAL(addon.getDataFolder());
		} catch (IOException e) {
			_.stacktrace(LogLevel.DEBUG, e);
		}
	}
	
	public void train() {
		brain.trainOnly("Hallo, ich bin PinkBot");
		brain.trainOnly("Rede mit mir!");
		brain.trainOnly("Wer bist du?");
		brain.trainOnly("Was willst du?");
	}
	
	public void train(String msg) {
		brain.trainOnly(msg);
	}
	
	public String reply(String msg) {
		String reply = brain.formulateReply(msg);
		
		if (reply == null) {
			reply = "Ich weiß nicht, was ich dazu sagen soll!";
		}
		return reply;
	}
}
