# Database Seed

The `seed.cypher` file contains the Cypher statements used to
initialize the Relationship Explorer graph database.

The seed script is organized into independent Cypher statements:

1. Technology nodes
2. Skill nodes
3. Role nodes
4. Project nodes
5. Technology → Skill relationships
6. Role → Skill relationships
7. Project → Technology relationships

Each statement can be executed independently in the CognoDB
query interface.

The application uses the following graph model:

Technology -[:REQUIRES]-> Skill
Role       -[:NEEDS]->    Skill
Project    -[:USES]->     Technology