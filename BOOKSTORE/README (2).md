# 📚 BOOKSTORE Application

A full-stack **Spring Boot REST API** for managing an online bookstore.  
Built completely **solo** as a learning + practice project, covering authentication, user roles, cart, and order management.

---

## 🚀 Features

### 👤 User Management
- User registration & login
- JWT-based authentication
- Role-based authorization (`ADMIN`, `CUSTOMER`)

### 📖 Bookstore
- CRUD operations for Books, Authors, Categories
- Book search & listing
- Stock management

### 🛒 Cart & Orders
- Add/remove items to Cart
- Place an Order from Cart
- Order Items mapped with price & quantity
- Track Order status (`PLACED`, `CONFIRMED`, `SHIPPED`)

### 🔐 Security
- Spring Security with JWT filter
- Role-based access (`ADMIN` endpoints restricted)

---

## 🏗️ Tech Stack

- **Backend:** Spring Boot 3, Spring Security, JPA/Hibernate  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Libraries:** Lombok, Jackson, JJWT  

---

## 📂 Project Structure

```
BOOKSTORE/
├── Controller/        # REST Controllers
├── Service/           # Business logic
├── Repository/        # JPA Repositories
├── Model/             # Entities (User, Book, Order, etc.)
├── Security/          # JWT filter & config
├── resources/
│   └── application.properties
└── BookstoreApplication.java
```

---

## ⚡ API Endpoints

### 🔑 Auth
- `POST /auth/register` – Register new user  
- `POST /auth/login` – Login & get JWT  

### 📚 Books
- `GET /books` – Get all books  
- `POST /admin/addbook` – Add new book (Admin only)  
- `DELETE /admin/book/{id}` – Delete book  

### 🛒 Cart
- `POST /cart/add` – Add item to cart  
- `GET /cart/{userId}` – View user’s cart  

### 📦 Orders
- `POST /Bookstore/placeOrder` – Place order from cart  
- `GET /Bookstore/orders/{userId}` – Get user’s orders  

---

## ⚙️ Setup & Run

1. Clone repo:
   ```sh
   git clone https://github.com/your-username/BOOKSTORE.git
   ```
2. Configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=yourSecretKey
   ```
3. Build & run:
   ```sh
   mvn spring-boot:run
   ```

---

## 📌 Notes

- After placing an order, user’s **cart is cleared**.  
- Uses `@JsonIgnore` and Lombok annotations to avoid infinite recursion.  
- Still a **learning project** → lacks DTOs, validations, and tests.  

---

## 📊 Project Evaluation

- **Feature coverage:** 60–65% of a real-world bookstore backend.  
- **Strengths:** End-to-end flow (auth → catalog → cart → orders), JWT security, relational modeling.  
- **Gaps:** No payments, no DTO layer, minimal validation, no automated tests.  

---

## 👨‍💻 Author

Developed solo by **Karaka Karthik** ✨  
As a practice project to strengthen **Java, Spring Boot, JPA, Security, and MySQL** skills.
