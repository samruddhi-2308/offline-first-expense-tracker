# Offline-First Expense Tracker

A production-grade Android application designed with an **offline-first architecture**, enabling reliable expense tracking and monthly financial aggregation without network dependency.

---

## 🚀 Overview

This project demonstrates end-to-end Android engineering practices including modern UI development, clean architecture, and efficient local data persistence.

The application ensures **zero data loss**, smooth UI performance, and predictable user flows under real-world constraints such as intermittent connectivity.

---

## 🧱 Architecture

- **MVVM (Model–View–ViewModel)** for clear separation of concerns
- **Repository pattern** to abstract data sources
- **Offline-first design** using Room as the single source of truth

UI (Compose)

↓

ViewModel

↓

Repository

↓

Room Database


---

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Persistence:** Room Database
- **Concurrency:** Kotlin Coroutines
- **Lifecycle Management:** ViewModel, LiveData
- **Build System:** Gradle (Kotlin DSL)

---

## ✨ Key Features

- 📌 Add and persist expenses locally
- 📊 Monthly expense aggregation and summary
- ⚡ Asynchronous database operations using Coroutines
- 📴 Fully functional without internet connectivity
- ✅ Input validation and UX feedback
- 🧠 Optimized data queries for aggregation performance

---

## 📈 Engineering Highlights

- Implemented **coroutine-based asynchronous I/O** to avoid main thread blocking
- Designed **transaction-safe local persistence** using Room
- Built **O(n)** monthly aggregation logic for financial insights
- Ensured lifecycle-aware state management with ViewModels
- Followed clean code and modular structure for scalability

---

## 📷 Screenshots

<img width="377" height="814" alt="image" src="https://github.com/user-attachments/assets/dc088d8e-cbed-4ba7-84b4-2ad29d922ba3" /> 
<img width="362" height="817" alt="image" src="https://github.com/user-attachments/assets/a3440dee-b9a5-452a-bf26-c2c44626a6e7" /> 
<img width="394" height="833" alt="image" src="https://github.com/user-attachments/assets/e9b9e81f-e0d9-4af9-a037-2c735abf8a37" />
<img width="398" height="833" alt="image" src="https://github.com/user-attachments/assets/3a5dcc90-f797-4877-a994-fbbe8108119d" /> 


---

## 🧪 Testing Status

- Manual functional testing completed
- Offline persistence verified
- Input validation and edge cases tested

---

## 🔮 Future Enhancements

- Category-based analytics
- Data export (CSV / PDF)
- Cloud sync (optional)
- Charts and visual insights

---

## 👩‍💻 Author

**Samruddhi Lakare**  
Computer Engineering Student  
Focused on building scalable, real-world software systems

---

## 📄 License

This project is open-source and available under the MIT License.
