
var name = window.location
document.session.name.value = name
 
var text = document.session.name.value
 
var ws = new WebSocket("ws://localhost:33333/");
 
ws.onopen = function () {
     document.write("Start! <br>");
    if (name != null) {
        document.write("Connected to websocket server! <br>");
        ws.send("Identification|" +QueryString.name+"|" + QueryString.pw);
        document.write("Sent data: Identification|" +QueryString.name+"|" + QueryString.pw + "<br>");
    }
 
};
 
ws.onmessage = function (evt) {
    document.write(event.data + "<br>");
};
 
ws.onclose = function (event) {
    alert("Closed! " + event.reason);
};
 
ws.onerror = function (err) {
    alert("Error: " + err);
};
 
function delineate(str) {
    theleft = str.indexOf("=") + 1;
    theright = str.lastIndexOf("&");
    return (str.substring(theleft, theright));
}

var QueryString = function () {
  var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
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
} ();