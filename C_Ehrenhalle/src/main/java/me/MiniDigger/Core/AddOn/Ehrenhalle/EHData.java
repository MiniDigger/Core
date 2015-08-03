package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class EHData {
	private Map<String, Double> donations = new HashMap<>();

	public void load() throws SQLException {
		String select = "SELECT * FROM `donation`";
		SQLQuery selectQ = new CoreSQLQuery(select);
		PreparedStatement selectS = selectQ.getStatement();
		ResultSet r = selectS.executeQuery();

		int i = 0;
		while (r.next()) {
			i++;
			donations.put(r.getString("name"), r.getDouble("amount"));
		}

		donations = sortByValue(donations);
		
		System.out.println(i + " dontatior loaded");
	}

	public void add(String name, double amount) throws SQLException {
		if (donations.containsKey(name)) {
			donations.put(name, donations.remove(name) + amount);
		} else {
			donations.put(name, amount);
		}

		String delete = "DELETE FROM `donation` WHERE `name` = ?";
		String insert = "INSERT INTO `donation`(`name`, `amount`) VALUES (?,?)";

		SQLQuery deleteQ = new CoreSQLQuery(delete);
		PreparedStatement deleteS = deleteQ.getStatement();
		deleteS.setString(0, name);
		deleteS.execute();
		deleteS.close();
		deleteQ.kill();

		SQLQuery insertQ = new CoreSQLQuery(insert);
		PreparedStatement insertS = insertQ.getStatement();
		insertS.setString(0, name);
		insertS.setDouble(1, amount);
		insertS.execute();
		insertS.close();
		insertQ.kill();
	}

	public Map<String, Double> getDonations() {
		return donations;
	}

	public double get(String string) {
		return donations.get(string);
	}

	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
