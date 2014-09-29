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
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;

public class DevCompleter {
	
	private List<String> randomShit() {
		final List<String> result = new ArrayList<>();
		
		int r = Core.getCore().getRandomUtil().nextInt(100);
		
		if (r < 50) {
			r += 20;
		}
		
		for (int i = 0; i < r; i++) {
			result.add(Core.getCore().getRandomUtil().nextString());
		}
		
		return result;
	}
	
	@Completer(name = "dev")
	public List<String> devC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.startServer")
	public List<String> startServerC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.startClient")
	public List<String> startClientC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.stopClient")
	public List<String> stopClientC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.stopServer")
	public List<String> stopServerC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.sendToServer")
	public List<String> sendToServerC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.reload")
	public List<String> reloadC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.reloadPl")
	public List<String> reloadPlC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.sendTestPacket")
	public List<String> sendTestPacketC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.getUUID")
	public List<String> getUUIDC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.timeTest")
	public List<String> timeTestC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.holoUrl")
	public List<String> holoUrlC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.fakeBlock")
	public List<String> fakeBlockC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.pack")
	public List<String> packC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.hideTag")
	public List<String> hideTagC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.showTag")
	public List<String> showTagC(final CommandArgs args) {
		return randomShit();
	}
	
	@Completer(name = "dev.rest")
	public List<String> restC(final CommandArgs args) {
		return randomShit();
	}
}
