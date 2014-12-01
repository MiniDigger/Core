var name = window.location
document.session.name.value = name

var text = document.session.name.value

var ws = new WebSocket("ws://localhost:33333/");

ws.onopen = function() {
	echo("Start!");
	if (name != null) {
		echo("Connected to websocket server!");
		ws.send("Identification|" + QueryString.name + "|" + QueryString.pw);
		echo("Sent data: Identification|" + QueryString.name + "|"
				+ QueryString.pw);
	}

};

function echo(str) {
	document.getElementById('console').innerHTML = str + "<br>"
			+ document.getElementById('console').innerHTML;
}

function send(str) {
	echo(str);
	ws.send(str);
}

ws.onmessage = function(evt) {
	echo(event.data);
};

ws.onclose = function(event) {
	alert("Closed! " + event.reason);
};

ws.onerror = function(err) {
	alert("Error: " + err);
};

function delineate(str) {
	theleft = str.indexOf("=") + 1;
	theright = str.lastIndexOf("&");
	return (str.substring(theleft, theright));
}

var QueryString = function() {
	var query_string = {};
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (typeof query_string[pair[0]] === "undefined") {
			query_string[pair[0]] = pair[1];
		} else if (typeof query_string[pair[0]] === "string") {
			var arr = [ query_string[pair[0]], pair[1] ];
			query_string[pair[0]] = arr;
		} else {
			query_string[pair[0]].push(pair[1]);
		}
	}
	return query_string;
}();