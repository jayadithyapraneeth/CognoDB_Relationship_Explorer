// Explore Relations
// Parameters:
// $selectednodename -> name of selected node
// $selectednodetype -> expected node label

MATCH (selected {name: $selectednodename})-[r]-(related) WHERE $selectednodetype IN labels(selected) RETURN labels(related)[0] AS relatedType, related.name AS relatedName