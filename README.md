# AgroTree-ML: Crop Recommendation System

A Machine Learning application built in **Java** using the **Weka** framework. This project utilizes a Decision Tree algorithm (J48) to recommend the most suitable crop based on real-time soil and weather parameters.

This project was developed for **Comp338** (Project 2).

---

## 🚀 Features

* **5-Fold Cross-Validation:** Automatically evaluates the model's performance and calculates the average Accuracy, Precision, and Recall.
* **Interactive CLI:** A simple command-line interface that allows users to input custom environmental data and get instant crop recommendations.
* **Clean Architecture:** Well-structured code separated into the execution entry point (`Main.java`) and the model logic (`DecisionTreeModel.java`).

---

## 📊 Dataset Parameters

The model evaluates **7 key inputs** to make a prediction:
1. **N, P, K:** Nitrogen, Phosphorous, and Potassium ratios in the soil.
2. **Temperature:** Air temperature in Celsius (°C).
3. **Humidity:** Relative humidity percentage (%).
4. **pH:** Soil acidity/alkalinity value.
5. **Rainfall:** Total rainfall volume in millimeters (mm).

---

## 🛠️ Setup & Requirements

1. Make sure the **Weka library** (.jar) is added to your project dependencies.
2. Update the dataset absolute path inside `Main.java` to match your local file location:
```java
   String path = "C:\\Your\\Path\\To\\crop_recommendation.csv";
