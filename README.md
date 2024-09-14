# Task manager  

A task Management Software made for HCLTech 
  
## Features:
Most features require user authentication.  
Unauthorized users can only access the welcome page and the login or registration panel.

**Admin (Manager) Capabilities:**
- Create tasks and assign them to any user.
- Mark tasks as completed or uncompleted.
- View a list of all users with the option to delete users.
- View a list of all tasks with the ability to edit or delete them.

**Common User (Employee) Capabilities:**
- Create tasks for themselves only.
- View the list of all users without any modification permissions.
- View all tasks, but can only edit or delete tasks they are responsible for.
- Mark their own tasks as completed or uncompleted.

**Every Authorized User Can:**
- View their own profile.

## Technologies Used:
- Spring Boot
- Spring Security
- H2 Database
- Maven
- Thymeleaf

## Test users

`admin@mail.com`  password: `112233`  
`Jason@mail.com`  password: `112233`  
`linda@mail.com`  password: `112233`
