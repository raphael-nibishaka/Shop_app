# Binary Supermarket System

A Spring Boot-based supermarket management system that provides a robust backend for managing supermarket operations.

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- MySQL Database
- Lombok
- Jakarta Mail

## 📋 Prerequisites

- JDK 17 or higher
- Maven
- MySQL Server
- IDE (preferably IntelliJ IDEA or Eclipse)

## 🛠️ Installation

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Configure MySQL database:
   - Create a new database
   - Update the database configuration in `application.properties`

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## 🔧 Configuration

The application uses Spring Security for authentication and authorization. The current configuration allows all requests by default, but you can modify the security settings in `SecurityConfig.java` to implement proper authentication and authorization.

## 📦 Project Structure

```
src/main/java/URaphael/raphy/
├── config/         # Configuration classes
├── controller/     # REST controllers
├── model/         # Entity classes
├── repository/    # Data access layer
├── service/       # Business logic layer
└── RaphyApplication.java  # Main application class
```

## 🔐 Security

The application uses Spring Security for handling authentication and authorization. The current configuration is set to permit all requests, but it can be customized based on your requirements.

## 📧 Email Support

The application includes email functionality using Jakarta Mail for sending notifications and alerts.

## 🧪 Testing

The project includes Spring Boot Test dependencies for unit and integration testing.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support, please open an issue in the repository or contact the development team.
