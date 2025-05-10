# Garda Station System - CA_2 Project

![Language](https://img.shields.io/badge/Language-Java-blue)
![Status](https://img.shields.io/badge/Project%20Status-Completed-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)


This is a command-line Java application that simulates the organisational structure of an Irish Garda Station.
The program allows users to manage Gardaí, assing maangers and departments,
and perform operations such as sorting, searching, generating, and exporting reports.

---

## 🛠 Features ![Feature Badge](https://img.shields.io/badge/-Key%20Functions-blue)

- Add a new Garda with selected manager type and department
- Generate random Gardaí with valid structrure
- Display all Gardaí in formatted table view
- Sort Garda list alphabetically using **recursive bubble sort**
- Search for a Garda using **binary recursive search**
- Delete Garda records by name
- Export list to `Garda_Report.txt`
- Load names from external `Applicants_Form.txt`file
- Display Gardaí sorted by Manager or Department type

---

## 📂 Project Structure ![Structure Badge](https://img.shields.io/badge/-Organised%20Modules-lightgrey)

- `Garda.java` -> Core class representing a Garda
- `Manager.java` & subtypes -> Represents manaerial hierarcy
- `Department.java` & subtypes -> Represents Garda units 
- `MyArrayList.java` -> Custom generic list class with sorting/searching 
- `GardaStationSystem_CA2_455.java` -> Main application logic and menu
- `MenuOption.java` -> Enum for structure menu options
- `Applicants_Form.txt` -> Dummy input file with random names 

---

## 💻 How to Run ![Run Badge](https://img.shields.io/badge/-Console%20App%20Steps-yellow)

1. Open in **NetBeans** (or your preferred IDE)
2. Ensure the file `Applicants_Form.txt` is in the **project root**
3. Click **Run Project**
4. Interact using the numbered menu system

---

## 📤 Export Report ![Export Badge](https://img.shields.io/badge/-Generates%20TXT%20Report-green)

Exports Garda list to a formmatted text file: `Garda_Report.txt`
- Includes columns for Name, Manager, Department
- Keeps exported data aligned in table format

---

## 👨‍🎓 Student Info

- **Student ID**: 2024455 
- **Student Name**: Kamil Yildiz 
- **Assignment**: CA_2 – Java Console Application  
- **Course**: Algorithms and Constructs (Object-Oriented Programming) 
- **College**: College of Computing Technology (CCT) 

---

## 🖊️ Editable Diagrams (draw.io)

- [01_Add_a_New_Garda](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/01_Add_a_New_Garda.drawio)
- [02_Generate_Random_Gardai](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/02_Generate_Random_Gardai.drawio)
- [03_Sort_Gardaí_Alphabetically](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/03_Sort_Garda%C3%AD_Alphabetically.drawio)
- [04_Search_Garda_by_Name](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/04_Search_Garda_by_Name.drawio)
- [05_Export_Garda_List](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/05_Export_Garda_List.drawio)
- [06_GardaStationSystem_Class_Diagram](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/06_GardaStationSystem_Class_Diagram.drawio)
- [07_Add_Garda_Activity_Diagram](https://app.diagrams.net/?url=https://raw.githubusercontent.com/Kml251/GardaStationSystem_CA2_455/Diagrams_Folder/07_Add_Garda_Activity_Diagram.drawio)

---

## 🔗 GitHub Repository

> https://github.com/Kml251/GardaStationSystem_CA2_455