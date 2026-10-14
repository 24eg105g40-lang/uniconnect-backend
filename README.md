# UniConnect - Spring Boot Backend API 🎓

A robust, enterprise-grade Spring Boot backend for the UniConnect academic platform, powered by **Neon Serverless PostgreSQL** and **JWT Bearer Authentication**.

---

## 🚀 Tech Stack
- **Framework:** Spring Boot 4.x / Java 17+
- **Database:** Neon PostgreSQL (with JPA/Hibernate)
- **Security:** JWT (JSON Web Tokens) with Role-Based Access Control (RBAC: ADMIN & STUDENT)
- **Deployment Platform:** Render (Dockerized web service)

---

## 🛠 Local Setup & Running in IntelliJ IDEA

1. **Open in IntelliJ IDEA:**
   - Launch IntelliJ IDEA.
   - Click **Open** and select the `uniconnect-backend` folder (`C:\Users\user\IdeaProjects\uniconnect-backend`).
   - IntelliJ will automatically detect the Maven project and load dependencies.

2. **Configure Database (Neon PostgreSQL):**
   - In `src/main/resources/application.properties`, configure your Neon database connection string:
     ```properties
     spring.datasource.url=jdbc:postgresql://<your-neon-host>.neon.tech/<dbname>?sslmode=require
     spring.datasource.username=<neon-username>
     spring.datasource.password=<neon-password>
     ```

3. **Run Application:**
   - Open `src/main/java/com/educonnect/educonnectbackend/EduconnectBackendApplication.java`.
   - Click the green **Run** button next to the `main` method.
   - The backend starts on `http://localhost:8080`.

---

## 🔑 Pre-seeded Demo Credentials
On startup, initial records are automatically seeded:
- **Admin Portal:** `Username: admin` | `Password: admin123` (Role: `ADMIN`)
- **Student Portal:** `Username: student` | `Password: student123` (Role: `STUDENT`)

---

## 📡 API Endpoints

### Authentication
- `POST /api/auth/login` - Authenticate user and receive JWT Bearer token
- `POST /api/auth/register` - Register a new student or admin account

### Role Dashboards
- `GET /api/admin/students` - Retrieve all registered students for Admin view
- `GET /api/student/profile` - Retrieve authenticated student profile info

### Student Resource Management
- `GET /api/students` - Paginated & sorted list of students
- `GET /api/students/{id}` - Fetch single student by ID
- `POST /api/students` - Create new student with Department, Address, and Courses
- `PUT /api/students/{id}` - Update student record
- `DELETE /api/students/{id}` - Remove student

---

## ☁️ Deployment on Render

1. Push this repository to your GitHub.
2. In [Render Dashboard](https://dashboard.render.com), click **New +** -> **Web Service**.
3. Connect your `uniconnect-backend` GitHub repository.
4. Set **Runtime** to `Docker` (or Environment: `Java`).
5. Add Environment Variables:
   - `PORT`: `8080`
   - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://<your-neon-endpoint>.neon.tech/neondb?sslmode=require`
   - `SPRING_DATASOURCE_USERNAME`: `<your-neon-user>`
   - `SPRING_DATASOURCE_PASSWORD`: `<your-neon-password>`
6. Click **Deploy Web Service**.
