package me.MiniDigger.AddOn.ChatBot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jibble.jmegahal.JMegaHal;

public class jMegaHalHandler {
	
	private JMegaHal	 brain;
	private ChatBotAddOn	addon;
	
	public jMegaHalHandler(ChatBotAddOn addon) {
		this.addon = addon;
	}
	
	public void save() throws IOException {
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(addon.getDataFolder(), "brain.ser")));
		out.writeObject(brain);
		out.close();
	}
	
	public void load() throws ClassNotFoundException, IOException {
		File file = new File(addon.getDataFolder(), "brain.ser");
		
		if(!file.exists()){
			brain = new JMegaHal();
			train();
			return;
		}
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		brain = (JMegaHal) in.readObject();
		in.close();
		
		if (brain == null) {
			brain = new JMegaHal();
			train();
		}
	}
	
	public void train() {
		brain.add("Hallo, ich bin PinkBot");
		brain.add("Rede mit mir!");
		brain.add("Wer bist du?");
		brain.add("Was willst du?");
	}
	
	public void train(String msg) {
		brain.add(msg);
	}
	
	public String reply(String msg) {
		return brain.getSentence(msg);
	}
}
