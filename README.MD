# Review Microservice (ReviewMS)

Manages product reviews, replies to reviews, and supports event-driven review deletion.

---

## 🚀 Features

- Post Product Reviews  
- Fetch Reviews by Product  
- Post Replies to Reviews  
- Delete Reviews and Replies  
- Event-driven Review Deletion when Product is deleted  

---

## 📋 API Endpoints

| HTTP Method | Path                              | Description                     | Auth Required |
|------------|-----------------------------------|---------------------------------|-------------|
| POST       | /review                           | Post a new review              | Yes         |
| GET        | /review/{productId}              | Get all reviews for a product  | No          |
| POST       | /review/{reviewId}/reply         | Post a reply to a review       | Yes         |
| DELETE     | /review/{reviewId}               | Delete a review                 | Yes         |
| DELETE     | /review/{reviewId}/{replyId}     | Delete a reply                  | Yes         |

---

## ⚡ Event-Driven Design

- Listens to Kafka topic for product deletion events.  
- Automatically deletes all reviews related to a deleted product.  
- Decouples ReviewMS from ProductMS.  
- Ensures data consistency without direct service-to-service calls.

---

## 📊 HTTP Status Codes

| Status Code | Meaning                     |
|------------|-----------------------------|
| 200 OK     | Success                     |
| 201 Created| Resource created            |
| 204 No Content | Successful without content |
| 400 Bad Request | Validation error         |
| 401 Unauthorized | Invalid credentials     |
| 403 Forbidden | Insufficient role          |
| 500 Internal Server Error | Server issue    |

---

## ⚙️ Technologies

- Spring Boot  
- Spring Data JPA  
- Kafka for event messaging  
- MySQL  
- Lombok  

---

## 🚧 Future Plans

- Integrate the Auth service via UserMS
- Pagination support for reviews  
- Rate limiting to prevent abuse  
- Review moderation system (flag/report inappropriate content)  
- Metrics collection for review performance  
- Improved error handling  

---

## ⚡ Notes

- Reviews are strongly associated with products via productId.  
- Kafka ensures eventual consistency on product deletion.  
- Stateless design with JWT-based authentication handled by other microservices.
