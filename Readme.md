# Pomona Transit System 🌍🚌

A Java-based transit management system that simulates real-world public transportation scheduling. This project showcases backend CRUD operations, SQL joins, CLI-based interaction, and modular design.

## Features

- View and update trip offering schedules
- Assign and reassign drivers and buses
- Manage bus and driver registries
- Display trip stops with sequencing and travel times
- Log actual trip stop data (passenger counts, real start times)

## Tech Stack

- **Language:** Java
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Framework:** JDBC (Java Database Connectivity)

## Getting Started

### Prerequisites

- Java 17+
- PostgreSQL installed locally
- Maven installed (`mvn -v` to verify)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/GerardoG2/pomona-transit-system.git
   cd pomona-transit-system
   ```

2. Create the database and restore schema/data:

   ```bash
   createdb pomona_transit
   psql -U <your_pg_user> -d pomona_transit -f pomona_transit_dump.sql
   ```

3. Run the application:

   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.pomonatransit.App"
   ```

4. Enter the following when prompted:

   - PostgreSQL username
   - PostgreSQL password
   - Database name (typically `pomona_transit`)

## Why This Project?

This project demonstrates:

- Realistic backend logic for a transit system
- Familiarity with relational schemas and key constraints
- Secure database operations via JDBC
- Clean, modular object-oriented design
- Strong CLI interaction and user flow

## Author

**Gerardo Gutierrez**\
Applied Mathematics, Cal Poly Pomona\
Data Science & Computer Science Minor
