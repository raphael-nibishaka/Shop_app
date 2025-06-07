# Binary Supermarket System

A comprehensive Spring Boot-based supermarket management system that provides a robust backend for managing supermarket operations. This system helps streamline inventory management, sales tracking, and customer relationship management.

## 🌟 Features

- **Inventory Management**
  - Real-time stock tracking
  - Low stock alerts
  - Product categorization
  - Batch management
  - Expiry date tracking

- **Sales Management**
  - Point of Sale (POS) system
  - Sales history tracking
  - Daily/monthly/yearly reports
  - Discount management
  - Payment processing

- **Customer Management**
  - Customer profiles
  - Purchase history
  - Loyalty program
  - Customer feedback system

- **Employee Management**
  - Staff profiles
  - Role-based access control
  - Attendance tracking
  - Performance metrics

- **Reporting System**
  - Sales analytics
  - Inventory reports
  - Financial statements
  - Customer insights

## 🚀 Technologies Used

- **Backend Framework**
  - Java 17
  - Spring Boot 3.2.3
  - Spring Security
  - Spring Data JPA
  - Spring Web
  - Spring Validation

- **Database**
  - MySQL Database
  - Hibernate ORM

- **Development Tools**
  - Lombok
  - Jakarta Mail
  - Maven
  - Git

## 📋 Prerequisites

- **Development Environment**
  - JDK 17 or higher
  - Maven 3.6.x or higher
  - MySQL Server 8.0 or higher
  - IDE (preferably IntelliJ IDEA or Eclipse)
  - Git

- **System Requirements**
  - Minimum 4GB RAM
  - 2GB free disk space
  - Internet connection for dependencies

## 🛠️ Installation

1. **Clone the repository:**
```bash
git clone [repository-url]
cd binary-supermarket
```

2. **Configure MySQL databas e:**
   - Install MySQL Server if not already installed
   - Create a new database:
   ```sql
   CREATE DATABASE supermarket_db;
   ```
   - Update the database configuration in `src/main/resources/application.properties:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/supermarket_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project:**
```bash
mvn clean install
```

4. **Run the application:**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🔧 Configuration

### Security Configuration
The application uses Spring Security for authentication and authorization. Configure security settings in `SecurityConfig.java`:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Security configuration details
}
```

### Email Configuration
Configure email settings in `application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

## 📦 Project Structure

```
src/main/java/URaphael/raphy/
├── config/         # Configuration classes
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── controller/     # REST controllers
│   ├── ProductController.java
│   ├── SaleController.java
│   └── UserController.java
├── model/         # Entity classes
│   ├── Product.java
│   ├── Sale.java
│   └── User.java
├── repository/    # Data access layer
│   ├── ProductRepository.java
│   ├── SaleRepository.java
│   └── UserRepository.java
├── service/       # Business logic layer
│   ├── ProductService.java
│   ├── SaleService.java
│   └── UserService.java
└── RaphyApplication.java  # Main application class
```

## 🔐 Security

The application implements a comprehensive security system:

- **Authentication**
  - JWT-based authentication
  - Password encryption using BCrypt
  - Session management

- **Authorization**
  - Role-based access control (RBAC)
  - Custom security policies
  - API endpoint protection

## 📧 Email Support

The system includes email functionality for:
- Order confirmations
- Password reset
- Stock alerts
- System notifications
- Marketing communications

## 🧪 Testing

The project includes comprehensive testing:

- **Unit Tests**
  - Service layer testing
  - Repository layer testing
  - Controller testing

- **Integration Tests**
  - API endpoint testing
  - Database integration testing
  - Security testing

Run tests using:
```bash
mvn test
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style Guidelines
- Follow Java coding conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Write unit tests for new features

## 📞 Support

For support:
- Open an issue in the repository
- Contact the development team at support@binarysupermarket.com
- Join our community forum at forum.binarysupermarket.com

## 🔄 Updates and Maintenance

- Regular security updates
- Bug fixes
- Feature enhancements
- Performance optimizations

## 📚 Documentation

- API documentation available at `/swagger-ui.html`
- User manual available in the `docs` folder
- Developer guide available in the `docs/developer` folder
