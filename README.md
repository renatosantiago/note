# Note API
Simple notes web application where a user can save a note, view a list of his notes and delete a note. To use the app's features, the user must be logged in.
The api uses an in-memory h2 database.

# Access Token
you need a token to authenticate in the application, when we start the application a default user is generated with the following credentials which we will use to get the token.

> **username:** alex@gmail.com 

> **password:** 123456

### Using the postman, follow the steps below as shown in the pictures to get the token


> **set the api client credentials**

<img width="1017" alt="Captura de Tela 2023-01-20 às 21 59 02" src="https://user-images.githubusercontent.com/3075542/213834898-dbe3feb7-dc2b-4c25-8ab0-d6e504b28646.png">


***


> **set the user credentials**

<img width="1018" alt="Captura de Tela 2023-01-20 às 21 59 52" src="https://user-images.githubusercontent.com/3075542/213835239-11c0066f-ed15-433a-b512-d6ccd6b6398f.png">


***


> **send the request to generate the token**

<img width="1018" alt="Captura de Tela 2023-01-20 às 22 44 58" src="https://user-images.githubusercontent.com/3075542/213835555-67cc1f34-0e3d-4b23-865e-294a453ed780.png">

# Save Note

> **method:** POST

> **url:** localhost:8080/note/save

> **body**
`{`
    `"description": "test saving a simple note",`
    `"userId": 1`
`}`

<img width="1010" alt="Captura de Tela 2023-01-20 às 23 00 12" src="https://user-images.githubusercontent.com/3075542/213836705-a12bda33-ef6b-4414-b8d9-e4131df30a4e.png">

# List Notes

> **method:** GET

> **url:** localhost:8080/note/list/1

<img width="1018" alt="Captura de Tela 2023-01-20 às 23 03 06" src="https://user-images.githubusercontent.com/3075542/213837668-d0cf5d64-e19c-45c8-8f35-26f33c639ee4.png">

# Delete note

> **method:** DELETE

> **url:** localhost:8080/note/delete/1

<img width="1014" alt="Captura de Tela 2023-01-20 às 23 07 01" src="https://user-images.githubusercontent.com/3075542/213838161-2c401793-2e18-4907-a5a1-dd1233f06058.png">
