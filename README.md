# 🎓 StudentCRUD API

> Manage student records with ease — create, read, update, and delete students via a clean REST API built on **Spring Boot 3** + **MySQL**.

---

## ⚡ Quick Start

```bash
# 1. Clone & enter the project
git clone <your-repo-url>
cd studentcrud

# 2. Configure your database
# Edit application.properties or .env with your MySQL credentials

# 3. Fire it up 🚀
./mvnw spring-boot:run
```

> App runs at → `http://localhost:8080`

---

## 🛠️ Tech Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| Language    | Java 21                           |
| Framework   | Spring Boot 3.4.5                 |
| Database    | MySQL + Spring Data JPA           |
| Build Tool  | Maven                             |
| Extras      | DevTools (hot reload), Actuator   |

---

## 📦 Project Structure

```
studentcrud/
└── src/main/java/in/strikes/studentcrud/
    ├── controller/   → StudentApi.java       (HTTP layer)
    ├── service/      → StudentService.java   (business logic)
    ├── repository/   → StudentRepository.java (DB queries)
    ├── entity/       → Student.java          (data model)
    └── StudentcrudApplication.java
```

---

## 🧑‍🎓 Student Model

```json
{
  "id": 1,
  "name": "Akash",
  "email": "akash@example.com",
  "age": 21,
  "rollNo": 101,
  "subject": "Computer Science",
  "deleted": false
}
```

> `deleted` is managed internally — you never need to set it manually.

---

## 🔌 API Endpoints

Base URL: `http://localhost:8080/api/students`

### ➕ Create a Student
```http
POST /api/students/create
Content-Type: application/json

{
  "name": "Akash",
  "email": "akash@example.com",
  "age": 21,
  "rollNo": 101,
  "subject": "Computer Science"
}
```
**Response `201 Created`**
```json
{
  "id": 1,
  "name": "Akash",
  "email": "akash@example.com",
  "age": 21,
  "rollNo": 101,
  "subject": "Computer Science",
  "deleted": false
}
```

---

### 🔍 Get a Student by ID
```http
GET /api/students/get?id=1
```
**Response `200 OK`** → returns the student object
**Response `404 Not Found`** → student doesn't exist or is soft-deleted

---

### 📋 Get All Students
```http
GET /api/students/getAll
```
**Response `200 OK`** → returns array of all active (non-deleted) students
**Response `404 Not Found`** → no students found

---

### ✏️ Update a Student
```http
PUT /api/students/update?id=1
Content-Type: application/json

{
  "name": "Akash Pandey",
  "email": "akash.new@example.com",
  "age": 22,
  "rollNo": 101,
  "subject": "Data Science"
}
```
**Response `200 OK`** → returns updated student
**Response `404 Not Found`** → student not found

---

### 🗑️ Hard Delete
```http
DELETE /api/students/delete?id=1
```
**Response `200 OK`** → `"Record Deleted"` — permanently removes from DB
**Response `404 Not Found`** → student not found

---

### 👻 Soft Delete
```http
PATCH /api/students/softDelete?id=1
```
**Response `200 OK`** → `"Record deleted softly"` — sets `deleted = true`, keeps data in DB
**Response `404 Not Found`** → student not found or already soft-deleted

---

## 🤔 Hard Delete vs Soft Delete

| | Hard Delete | Soft Delete |
|---|---|---|
| Data in DB | ❌ Gone forever | ✅ Still there |
| Appears in `getAll` | ❌ No | ❌ No |
| Recoverable | ❌ No | ✅ Yes (manually) |
| Endpoint | `DELETE /delete` | `PATCH /softDelete` |

> Soft delete is great for audit trails, accidental deletions, or when you might need to restore records later.

---

## 🏥 Health Check

```http
GET /actuator/health
```
```json
{ "status": "UP" }
```

---

## ⚙️ Configuration

Set these in `application.properties` or your `.env` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```