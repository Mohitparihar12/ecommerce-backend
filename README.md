# E-Commerce Backend API

A beginner-friendly Spring Boot REST API starter project. It includes category and product management, product search, order placement, stock checks, JPA relationships, validation, and global exception handling.

## Technology
- Java 17
- Spring Boot 3.5.6
- Spring Web / REST
- Spring Data JPA + Hibernate
- H2 in-memory database
- Jakarta Bean Validation
- Maven

## Run in Eclipse
1. Extract `ecommerce-backend.zip`.
2. Open Eclipse and choose **File → Import → Maven → Existing Maven Projects**.
3. Select the extracted `ecommerce-backend` folder and finish.
4. Make sure Java 17 or later is configured.
5. Right-click `EcommerceBackendApplication.java` → **Run As → Java Application** (or Spring Boot App if available).
6. Wait for the console message showing Tomcat started on port 8080.
7. Test endpoints with Postman.

The H2 database is in memory. Data is reset when the application restarts.

## Database console
Open `http://localhost:8080/h2-console`

Use:
- JDBC URL: `jdbc:h2:mem:ecommerce`
- User Name: `sa`
- Password: leave blank

## API quick start

### 1. Create a category
`POST http://localhost:8080/api/categories`

Body → raw → JSON:
```json
{
  "name": "Electronics"
}
```

### 2. Create a product
`POST http://localhost:8080/api/products?categoryId=1`

Body:
```json
{
  "name": "Wireless Mouse",
  "description": "Bluetooth mouse",
  "price": 799.00,
  "stock": 25
}
```
Do not include `id` or `category` in this request. `categoryId` is supplied in the URL.

### 3. Read products
`GET http://localhost:8080/api/products`

### 4. Search products
`GET http://localhost:8080/api/products/search?name=mouse`

### 5. Get products by category
`GET http://localhost:8080/api/products/by-category/1`

### 6. Update a product
`PUT http://localhost:8080/api/products/1?categoryId=1`

Body:
```json
{
  "name": "Wireless Mouse Pro",
  "description": "Rechargeable Bluetooth mouse",
  "price": 999.00,
  "stock": 20
}
```

### 7. Delete a product
`DELETE http://localhost:8080/api/products/1`

### 8. Place an order
First create a category and product, then place an order:
`POST http://localhost:8080/api/orders`

Body:
```json
{
  "customerId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

The API checks stock, reduces stock, calculates the total, and saves the order and its items in one transaction.

### 9. Read an order
`GET http://localhost:8080/api/orders/1`

### 10. Read a customer's orders
`GET http://localhost:8080/api/orders/customer/1`

## All endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/categories` | Create category |
| GET | `/api/categories` | List categories |
| GET | `/api/categories/{id}` | Get category |
| PUT | `/api/categories/{id}` | Update category |
| DELETE | `/api/categories/{id}` | Delete category |
| POST | `/api/products?categoryId=1` | Create product |
| GET | `/api/products` | List products |
| GET | `/api/products/{id}` | Get product |
| PUT | `/api/products/{id}?categoryId=1` | Update product |
| DELETE | `/api/products/{id}` | Delete product |
| GET | `/api/products/search?name=mouse` | Search products |
| GET | `/api/products/by-category/{categoryId}` | Products in category |
| POST | `/api/orders` | Place order |
| GET | `/api/orders/{id}` | Get order |
| GET | `/api/orders/customer/{customerId}` | Customer order history |

## Important learning note
This is a learning MVP, not a production-ready store. It does not yet include real user accounts, authentication/JWT, a cart, payment integration, DTO-based response design, or concurrency-safe inventory locking. `customerId` is currently just an ID supplied by the API caller, not an authenticated customer. Add these after you understand the current code. Never store real payment-card information.

## Recommended next steps
1. Understand the Category CRUD flow.
2. Understand the Product-to-Category `@ManyToOne` relationship.
3. Understand how `OrderService` checks stock and uses `@Transactional`.
4. Add automated tests.
5. Add DTOs and pagination.
6. Add Spring Security + JWT and customer accounts.
7. Replace H2 with MySQL/PostgreSQL for persistent storage.
