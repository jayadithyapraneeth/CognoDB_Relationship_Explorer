// Load all graph nodes for the application's selection controls

MATCH (n) RETURN labels(n)[0] AS type, n.name AS name ORDER BY type, name