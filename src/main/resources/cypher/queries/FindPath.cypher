// Path Finder
// Parameters:
// $startnode    -> name of starting node
// $endnode      -> name of destination node
// Labels are supplied dynamically by the Java backend.


MATCH path = (start:%s {name: $startnode})-[*1..5]-(end:%s {name: $endnode}) RETURN path LIMIT 1
