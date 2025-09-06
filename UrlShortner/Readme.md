# URL Shortener

A simple URL Shortener application built with **Spring Boot**.  
It allows users to shorten long URLs, redirect to original URLs using short codes, and track click counts.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Improvements & Notes](#improvements--notes)

---

## Features

- Convert long URLs into short, easy-to-share links using **Base62 encoding**.
- Redirect short URLs to the original URLs.
- Track click counts for each URL.
- Null-safe and handles invalid short URLs gracefully.
- RESTful API with JSON responses.

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- MySQL (or H2 for testing)
- Lombok
- Maven

---

## Setup

1. Clone the repository:
```bash
git clone <https://github.com/karthikkaraka/UrlShortner>
cd Urlshortner
