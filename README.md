# Sistema de Gestion de Concesionario

Sistema desarrollado en Java con arquitectura MVC para gestion de concesionario de automoviles.

## Requisitos

- Java JDK 24
- MySQL 8.0+
- NetBeans IDE

## Librerias incluidas

- mysql-connector-j-9.5.0.jar
- itextpdf-5.5.13.3.jar

## Instalacion

### 1. Clonar repositorio
git clone [URL]
cd SGVConcesionario

### 2. Configurar base de datos
Ejecutar database/concesionario_db.sql en MySQL Workbench

### 3. Configurar conexion
Editar src/config/ConexionDB.java:
- URL, usuario y contrasena de MySQL

### 4. Abrir en NetBeans
- File → Open Project
- Las librerias se cargan automaticamente desde /lib

### 5. Ejecutar
- Clean and Build (Shift + F11)
- Run (F6)

## Funcionalidades

- Gestion de Clientes
- Gestion de Vendedores
- Gestion de Automoviles
- Registro de Ventas con generacion de PDF

## Autores

- Sergio Benjumea
- [Nombre companero]

