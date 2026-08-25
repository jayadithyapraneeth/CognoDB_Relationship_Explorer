# Relationship Explorer

A graph-based web application for exploring relationships between **\*\*Technologies, Skills, Roles, and Projects\*\*** using **\*\*CognoDB\*\*** and the **\*\*Neo4j Java Driver\*\***.

The application provides two primary graph exploration features:

&nbsp; - **\*\*Path Finder\*\*** – discovers a relationship path between any two nodes.

&nbsp; - **\*\*Explore Relations\*\*** – displays all direct relationships of a selected node.

The project demonstrates how a graph database can represent and query connected information more naturally than a conventional relational structure.

&nbsp;---

&nbsp;

&nbsp;## 📌 Project Overview

In technology and career-related datasets, entities such as technologies, skills, job roles, and projects are highly interconnected.

For example:

\`\`\`text

E-Commerce Order Management System

&nbsp; │

&nbsp; USES

&nbsp; ▼

&nbsp; Java

&nbsp; │

&nbsp; REQUIRES

&nbsp; ▼

&nbsp; OOP

&nbsp; ▲

&nbsp; NEEDS

&nbsp; │

&nbsp;Java Backend Developer \`

A relationship such as:

&nbsp; Project → Technology → Skill ← Role

can be represented and traversed naturally using a graph database.

**Relationship Explorer** provides a simple interface for exploring these connections interactively.

🎯 Objectives

\-------------------------------------------

The main objectives of the project are:

- Represent technology-related information as a graph.
- Model relationships between projects, technologies, skills, and roles.
- Find paths between two arbitrary nodes.
- Explore direct relationships of a selected node.
- Demonstrate the practical use of a graph database.
- Provide a simple web interface for graph traversal and exploration.
- Keep graph querying and processing on the backend while returningsimplified results to the frontend.

&nbsp;

🧩 Graph Data Model

\-----------------------------------------

The application uses four node types:

- Technology
- Skill
- Role
- Project

and three relationship types:

&nbsp;Technology ──\[REQUIRES\]──> Skill

&nbsp; Role ──\[NEEDS\]───────────> Skill

Project ──\[USES\]─────────> Technology

\### Graph Model Diagram

                  ┌───────────────┐
                  │    Project    │
                  └───────┬───────┘
                          │
                       USES
                          │
                          ▼
                  ┌───────────────┐
                  │  Technology   │
                  └───────┬───────┘
                          │
                       REQUIRES
                          │
                          ▼
                  ┌───────────────┐
                  │     Skill     │
                  └───────▲───────┘
                          │
                        NEEDS
                          │
                          │
                  ┌───────┴───────┐
                  │     Role      │
                  └───────────────┘

The graph model is intentionally small and focused so that the relationships remain easy to understand and demonstrate.

🔗 Example Relationship Chain

\------------------------------------------------

E-Commerce Order Management System

&nbsp; │

&nbsp; USES

&nbsp; ▼

&nbsp; Java

&nbsp; │

&nbsp; REQUIRES

&nbsp; ▼

&nbsp; OOP

&nbsp; ▲

&nbsp; NEEDS

&nbsp; │

Java Backend Developer

This allows the application to discover a relationship between a **project** and a **developer role** through intermediate graph nodes.

For example:

Project → Technology → Skill ← Role

The Path Finder can discover such connections without requiring the application to know the exact intermediate nodes beforehand.

✨ Features

\=======================

1\. Path Finder

\-------------------------------------

The Path Finder allows the user to select:

- A starting node
- A destination node

and searches the graph for a relationship path between them.

Example:

Java 🡪 REQUIRES 🡪 OOP 🡨 NEEDS 🡨 Java Backend Developer

The backend performs the graph traversal and converts the result into a human-readable representation before sending it to the frontend.

\### Example Queries

Some recommended examples are available directly from the home page so that the feature can be demonstrated quickly.

Examples include:

- E-Commerce Order Management System → OOP
- E-Commerce Order Management System → Java Backend Developer
- Java → Java Backend Developer
- Spring Boot → REST APIs
- Collaborative Task Management Dashboard → React

&nbsp;

2\. Explore Relations

\----------------------------------------

The Explore Relations feature allows the user to select a single node and inspect its **direct relationships**.

For example, selecting:

Java can reveal related:

- Technologies
- Skills
- Projects
- Roles

The backend retrieves the direct graph relationships and groups the results by node type before returning them to the frontend.

This provides a simple way to inspect the local neighbourhood of a node.

3\. Dynamic Node List

\------------------------------------------

The home page dynamically loads the available graph nodes from the database.

The node list contains:

**Technology**

**Skill**

**Role**

**Project**

The same dataset is used to populate the Path Finder and Explore Relations selection controls.

🏗️ System Architecture

\=================================

The application follows a simple Java web application architecture.

```
┌─────────────────────────┐
│        Frontend         │
│   HTML + JavaScript     │
└───────────┬─────────────┘
            │
            │ HTTP
            ▼
┌─────────────────────────┐
│        Servlets         │
│ ┌─────────────────────┐ │
│ │  HomePageServlet    │ │
│ │  FindPathServlet    │ │
│ │ ExploreRelations... │ │
│ └─────────┬───────────┘ │
└───────────┼─────────────┘
            │
            ▼
┌─────────────────────────┐
│      GraphService       │
│ ┌─────────────────────┐ │
│ │   Cypher Queries    │ │
│ │  Result Processing  │ │
│ └─────────┬───────────┘ │
└───────────┼─────────────┘
            │
            │ Neo4j Java Driver
            ▼
┌─────────────────────────┐
│        CognoDB          │
│     Graph Database      │
└─────────────────────────┘
````



### Request Flow

&nbsp; User

&nbsp; ↓

&nbsp;HTML / JavaScript

&nbsp; ↓

&nbsp;Servlet

&nbsp; ↓

&nbsp;GraphService

&nbsp; ↓

&nbsp;Neo4j Java Driver

&nbsp; ↓

&nbsp;CognoDB

&nbsp; ↓

&nbsp;Cypher Query Execution

&nbsp; ↓

&nbsp;Query Result

&nbsp; ↓

&nbsp;Java Backend Processing

&nbsp; ↓

&nbsp;JSON Response

&nbsp; ↓

&nbsp;Frontend

The backend performs the graph processing rather than exposing raw database results directly to the frontend.

🛠️ Technologies Used

\================================

Backend
------------------------------------

- Java
- Jakarta Servlets
- Apache Tomcat
- Neo4j Java Driver
- Gson
- Maven


Database
-----------------------------------

- CognoDB
- Cypher

Frontend
-----------------------------------

- HTML
- CSS
- JavaScript
- Fetch API

&nbsp;

Development Environment
--------------------------------------

- Eclipse IDE
- Apache Tomcat
- Git / GitHub

🗂️ Project Structure

\=================================

![Project Structure](/docs/screenshots/projectstructure.png)

🗄️ Database Design

\==================================

The graph contains four primary node labels:

Node 🡪 Purpose

Technology 🡪 Programming languages, frameworks, libraries and tools

Skill 🡪 Technical skills associated with technologies

Role 🡪 Backend development roles

Project 🡪 Example software projects



The relationships are:

Technology -\[:REQUIRES\]-> Skill 🡺 A technology is associated with a skill

Role -\[:NEEDS\]-> Skill 🡺 A role requires a skill

Project -\[:USES\]-> Technology 🡺 A project uses a technology



🔍 Why a Graph Database?

\===================================

A graph database is appropriate for this project because the primary problem is not simply storing entities—it is **understanding the relationships between entities.**

The important information in this application is represented through connections such as:

&nbsp; Project

&nbsp; USES

      ↓

Technology

REQUIRES

      ↓

Skill

&nbsp;↑

NEEDS

Role

The Path Finder depends on traversing these relationships.

With a graph database, nodes and relationships can be traversed directly, making relationship-oriented queries natural and easy to express using Cypher.

For example, the application can search for a bounded path between two nodes:

      MATCH path = (start:%s {name: \$startnode}) -\[\*1..5\]- (end:%s {name: \$endnode}) RETURN path LIMIT 1

The graph structure also makes it easier to extend the application later with additional node types or relationship types.

\### Why not simply use relational tables?

The same data could technically be represented using relational tables, but relationship traversal would require joins across multiple tables.

For this particular application, the relationships themselves are the central part of the data model.

Therefore, a graph representation provides a more natural model for:

- Relationship traversal
- Multi-hop connections
- Direct neighborhood exploration
- Extending the graph with new relationship types



🔎 Cypher Queries

\=============================

The main application queries are available in:

&nbsp; cypher/ 

      └── queries/ 
            ├── find-path.cypher 
            ├── explore-relations.cypher 
            └── load-nodes.cypher

\### Path Finder

&nbsp; MATCH path = (start:%s {name: \$startnode}) -\[\*1..5\]- (end:%s {name: \$endnode}) RETURN path LIMIT 1


\### Explore Direct Relation

MATCH (selected {name: \$selectednodename})-\[r\]-(related) WHERE \$selectednodetype IN labels(selected) RETURN labels(related)\[0\] AS relatedType, related.name AS relatedName

\### Load Nodes

&nbsp; MATCH (n) RETURN labels(n)\[0\] AS type, n.name AS name ORDER BY type, name



🌱 Database Seeding

\===============================

The graph dataset can be initialized using:

&nbsp; cypher/seed/seed.cypher

The seed data creates:

1. Technology nodes
2. Skill nodes
3. Role nodes
4. Project nodes
5. Technology → Skill relationships
6. Role → Skill relationships
7. Project → Technology relationships

The statements are organized independently because the CognoDB browser interface used during development accepts one request at a time.



🚀 Setup and Installation

\==============================

Prerequisites

\-------------------------

The following are required:

- Java
- Maven
- Apache Tomcat
- Eclipse IDE or another Java IDE
- A CognoDB instance
- Internet connectivity for the cloud database

1\. Clone the Repository
------------------------------------------

git clone <https://github.com/jayadithyapraneeth/CognoDB_Relationship_Explorer.git>


2\. Configure CognoDB
-----------------------------------------

Create or obtain a CognoDB instance and configure the connection information required by the application.

**Do not commit database credentials to GitHub.**

The required connection details should be supplied through the application's configuration/environment mechanism.

3\. Import the Project
----------------------------------------

Import the project into Eclipse as a Maven/Java web project.

Configure Apache Tomcat as the runtime server.

4\. Seed the Database
-----------------------------------------

Execute the statements from:

&nbsp; cypher/seed/seed.cypher

against the CognoDB instance.

The seed statements create the nodes and relationships required by the application.

5\. Build and Run
-----------------------------------------

Build the Maven project and deploy the generated web application to Apache Tomcat.

Open the application through the local Tomcat URL.

Example:

<http://localhost:8080/relationfinder>



🖥️ Application Screenshots

\=============================

Home Page
---------------------

The home page provides access to the two primary features:

- Find Path
- Explore Relations

![Home Page](/docs/screenshots/homepage.png)

It also provides recommended examples for quickly demonstrating successful graph traversal.

Path Finder
---------------------

The Path Finder displays the relationship path discovered between the selected nodes.

![Path Finder Page](/docs/screenshots/pathfinder1.png)

Explore Relations
---------------------------

The Explore Relations page displays the direct relationships of the selected node grouped by node type.

![Explore Relations 1](/docs/screenshots/explorerelations1.png)

![Explore Relations 2](/docs/screenshots/explorerelations2.png)

CognoDB Graph
-------------------------

The graph visualization demonstrates the actual nodes and relationships stored in CognoDB.

🎬 Demonstration – Demo Video

\========================

A screen-recorded demonstration of the application is provided separately as part of the project submission.

Demo video link: https://drive.google.com/file/d/1b-dQKqzAur6R2VbyPhOyQSgsdAMgPBon/view?usp=sharing

The demonstration covers:

1\. Home page

2\. Path Finder

3\. Recommended path examples

4\. Successful graph traversal

5\. Explore Relations

6\. Direct relationship retrieval

⚠️ Error and Empty States

\===========================

The application accounts for situations such as:

- No relationship existing between selected nodes
- A node having no direct relationships
- Backend request failures
- Database connectivity problems
- Invalid or missing selections

The application is designed to provide meaningful frontend feedback instead of exposing raw database errors to the user.

📌 Limitations

\=================

The current version is intentionally small and focused on demonstrating graph-based relationship exploration.

Current limitations include:

- Path Finder searches within a bounded traversal depth.
- The Path Finder currently displays one discovered path.
- The dataset is a curated demonstration dataset.
- The application focuses on a fixed set of node and relationship types.

These limitations also leave room for future extensions.

🔮 Future Enhancements

\========================

Possible future improvements include:

- Interactive graph visualization in the frontend
- Displaying multiple possible paths
- Configurable traversal depth
- Additional node and relationship types
- Graph filtering
- Search-based node exploration
- Path highlighting
- Larger real-world datasets
- Authentication and user-specific graph exploration

&nbsp;

👨‍💻 Project Purpose

\=====================

This project was developed as a minor project to demonstrate the practical application of:

- Graph data modelling
- Cypher querying
- Java backend development
- Jakarta Servlets
- Database connectivity
- Backend-to-frontend communication
- Graph traversal and relationship exploration

The project focuses on keeping the implementation simple while demonstrating why a graph-oriented data model is useful for relationship-heavy information.

📄 License

\===============

This project is developed for academic and educational purposes only.
