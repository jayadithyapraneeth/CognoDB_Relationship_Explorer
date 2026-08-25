// ============================================================
// Relationship Explorer - Database Seed Script
// ============================================================

// ------------------------------------------------------------
// 1. Create Technology nodes
// ------------------------------------------------------------

UNWIND [
    'Python',
    'Java',
    'React',
    'JDBC',
    'Spring',
    'Spring Boot',
    'Hibernate',
    'Maven',
    'Gradle',
    'JUnit',
    'Tomcat',
    'JPA',
    'Kafka',
    'Django',
    'Flask',
    'FastAPI',
    'NumPy',
    'Pandas',
    'PyTorch',
    'TensorFlow',
    'SQLAlchemy',
    'Jupyter',
    'Celery',
    'JSX',
    'Redux',
    'React Router',
    'Next.js',
    'Vite',
    'Webpack',
    'Babel',
    'TypeScript',
    'Material UI',
    'Axios'
] AS technology
MERGE (:Technology {name: technology})

// ------------------------------------------------------------
// 2. Create Skill nodes
// ------------------------------------------------------------

UNWIND [
    'Java',
    'OOP',
    'Multithreading',
    'Exception handling',
    'Collections',
    'Generics',
    'JDBC',
    'Concurrency',
    'Memory management',
    'Design patterns',
    'JVM basics',
    'SQL',
    'DB connectivity',
    'Dependency Injection',
    'Spring',
    'REST APIs',
    'ORM',
    'Build management',
    'Build automation',
    'Unit testing',
    'Web deployment',
    'Messaging',
    'Python',
    'Data structures',
    'File handling',
    'Generators',
    'Decorators',
    'Async programming',
    'API development',
    'Routing',
    'Typing',
    'Arrays',
    'Math',
    'Data handling',
    'Machine Learning basics',
    'Notebooks',
    'Data analysis',
    'Queues',
    'Async tasks',
    'React',
    'JavaScript',
    'HTML',
    'Components',
    'Props and state',
    'Hooks',
    'Event handling',
    'Conditional rendering',
    'State management',
    'Component lifecycle',
    'Reusability',
    'Performance optimization',
    'Form handling',
    'SSR',
    'Tooling',
    'Modules',
    'Bundling',
    'Transpiling',
    'ES6',
    'Styling',
    'HTTP',
    'APIs'
] AS skill
MERGE (:Skill {name: skill})

// ------------------------------------------------------------
// 3. Create Role nodes
// ------------------------------------------------------------

UNWIND [
    'Java Backend Developer',
    'Python Backend Developer'
] AS role
MERGE (:Role {name: role})

// ------------------------------------------------------------
// 4. Create Project nodes
// ------------------------------------------------------------

UNWIND [
    'E-Commerce Order Management System',
    'Student Performance Analysis System',
    'Collaborative Task Management Dashboard'
] AS project
MERGE (:Project {name: project})

// ------------------------------------------------------------
// 5. Create Technology -> Skill Relationships
// ------------------------------------------------------------

UNWIND [
    {technology: 'Java', skills: [
        'OOP',
        'Multithreading',
        'Exception handling',
        'Collections',
        'Generics',
        'Concurrency',
        'Memory management',
        'Design patterns',
        'JVM basics'
    ]},

    {technology: 'JDBC', skills: [
        'Java',
        'SQL',
        'DB connectivity'
    ]},

    {technology: 'Spring', skills: [
        'Java',
        'OOP',
        'Dependency Injection'
    ]},

    {technology: 'Spring Boot', skills: [
        'Java',
        'Spring',
        'REST APIs'
    ]},

    {technology: 'Hibernate', skills: [
        'Java',
        'SQL',
        'ORM'
    ]},

    {technology: 'Maven', skills: [
        'Java',
        'Build management'
    ]},

    {technology: 'Gradle', skills: [
        'Java',
        'Build automation'
    ]},

    {technology: 'JUnit', skills: [
        'Java',
        'Unit testing'
    ]},

    {technology: 'Tomcat', skills: [
        'Java',
        'Web deployment'
    ]},

    {technology: 'JPA', skills: [
        'Java',
        'SQL',
        'ORM'
    ]},

    {technology: 'Kafka', skills: [
        'Java',
        'Messaging'
    ]},

    {technology: 'Python', skills: [
        'Data structures',
        'OOP',
        'File handling',
        'Exception handling',
        'Generators',
        'Decorators',
        'Multithreading',
        'Async programming',
        'Unit testing',
        'API development'
    ]},

    {technology: 'Django', skills: [
        'Python',
        'OOP',
        'SQL'
    ]},

    {technology: 'Flask', skills: [
        'Python',
        'Routing',
        'REST APIs'
    ]},

    {technology: 'FastAPI', skills: [
        'Python',
        'REST APIs',
        'Typing'
    ]},

    {technology: 'NumPy', skills: [
        'Python',
        'Arrays',
        'Math'
    ]},

    {technology: 'Pandas', skills: [
        'Python',
        'Data handling',
        'SQL'
    ]},

    {technology: 'PyTorch', skills: [
        'Python',
        'Math'
    ]},

    {technology: 'TensorFlow', skills: [
        'Python',
        'Math',
        'Machine Learning basics'
    ]},

    {technology: 'SQLAlchemy', skills: [
        'Python',
        'SQL',
        'ORM'
    ]},

    {technology: 'Jupyter', skills: [
        'Python',
        'Notebooks',
        'Data analysis'
    ]},

    {technology: 'Celery', skills: [
        'Python',
        'Queues',
        'Async tasks'
    ]},

    {technology: 'React', skills: [
        'Components',
        'Props and state',
        'Hooks',
        'Event handling',
        'Conditional rendering',
        'State management',
        'Component lifecycle',
        'Reusability',
        'Performance optimization',
        'Form handling'
    ]},

    {technology: 'JSX', skills: [
        'JavaScript',
        'HTML',
        'Components'
    ]},

    {technology: 'Redux', skills: [
        'JavaScript',
        'State management',
        'React'
    ]},

    {technology: 'React Router', skills: [
        'React',
        'Routing',
        'JavaScript'
    ]},

    {technology: 'Next.js', skills: [
        'React',
        'JavaScript',
        'SSR'
    ]},

    {technology: 'Vite', skills: [
        'JavaScript',
        'Tooling',
        'Modules'
    ]},

    {technology: 'Webpack', skills: [
        'JavaScript',
        'Bundling',
        'Modules'
    ]},

    {technology: 'Babel', skills: [
        'JavaScript',
        'Transpiling',
        'ES6'
    ]},

    {technology: 'TypeScript', skills: [
        'JavaScript',
        'Typing',
        'React'
    ]},

    {technology: 'Material UI', skills: [
        'React',
        'Styling',
        'Components'
    ]},

    {technology: 'Axios', skills: [
        'JavaScript',
        'HTTP',
        'APIs'
    ]}
] AS mapping

MATCH (t:Technology {name: mapping.technology})

UNWIND mapping.skills AS skillName

MATCH (s:Skill {name: skillName})

MERGE (t)-[:REQUIRES]->(s)

// ------------------------------------------------------------
// 6. Create Role -> Skill Relationships
// ------------------------------------------------------------

UNWIND [
    {role: 'Java Backend Developer', skills: [
        'Java',
        'OOP',
        'Multithreading',
        'Exception handling',
        'Collections',
        'JDBC',
        'SQL',
        'Spring',
        'Hibernate',
        'REST APIs'
    ]},

    {role: 'Python Backend Developer', skills: [
        'Python',
        'OOP',
        'Exception handling',
        'SQL',
        'Flask',
        'Django',
        'FastAPI',
        'API development',
        'Async programming',
        'Data handling'
    ]}
] AS mapping

MATCH (r:Role {name: mapping.role})

UNWIND mapping.skills AS skillName

MATCH (s:Skill {name: skillName})

MERGE (r)-[:NEEDS]->(s)

// ------------------------------------------------------------
// 2. Create Project -> Technology Relationships
// ------------------------------------------------------------

UNWIND [
    {project: 'E-Commerce Order Management System', technologies: [
        'Java',
        'Spring Boot',
        'JDBC',
        'Hibernate',
        'JPA',
        'Maven',
        'Tomcat',
        'Kafka',
        'JUnit'
    ]},

    {project: 'Student Performance Analysis System', technologies: [
        'Python',
        'Pandas',
        'NumPy',
        'Jupyter',
        'FastAPI',
        'PyTorch'
    ]},

    {project: 'Collaborative Task Management Dashboard', technologies: [
        'React',
        'JSX',
        'Redux',
        'React Router',
        'TypeScript',
        'Vite',
        'Material UI',
        'Axios'
    ]}
] AS mapping

MATCH (p:Project {name: mapping.project})

UNWIND mapping.technologies AS technologyName

MATCH (t:Technology {name: technologyName})

MERGE (p)-[:USES]->(t)

