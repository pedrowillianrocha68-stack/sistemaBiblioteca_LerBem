📚 Sistema Biblioteca LerBem — Backend
API REST desenvolvida em Java com Spring Boot para gerenciamento de uma biblioteca digital, com autenticação JWT, controle de usuários, livros e empréstimos.

🛠️ Tecnologias Utilizadas

Java 17+
Spring Boot
Spring Security + JWT
Maven
JPA / Hibernate


📁 Estrutura do Projeto
src/main/java/com/pedrowillianrocha68stack/
│
├── controller/          # Endpoints REST
├── DTO/                 # Objetos de transferência de dados
│   ├── EmprestimoResponseDTO.java
│   ├── LivroRequestDTO.java
│   ├── LivroResponseDTO.java
│   ├── UsuarioRequestDTO.java
│   └── UsuarioResponseDTO.java
│
├── exception/           # Tratamento de exceções
├── model/               # Entidades do banco de dados
│   ├── Emprestimo.java
│   ├── Livro.java
│   └── Usuarios.java
│
├── repository/          # Interfaces JPA
├── security/            # Configuração de segurança
│   ├── JwtFilter.java
│   └── JwtUtil.java
│
├── service/             # Regras de negócio
├── view/                # (Camada de visualização, se aplicável)
└── App.java             # Classe principal

⚙️ Como Executar
Pré-requisitos

Java 17 ou superior instalado
Maven instalado
Banco de dados configurado (verifique application.properties)

Passos
bash# Clone o repositório
git clone https://github.com/pedrowillianrocha68-stack/frontend-biblioteca-lerbem.git

# Acesse a pasta do projeto
cd sistemaBiblioteca_LerBem

# Compile e execute com Maven
mvn spring-boot:run
A API ficará disponível em: http://localhost:8080

🔐 Autenticação
O sistema utiliza JWT (JSON Web Token) para autenticação. Após o login, o token deve ser enviado no header de todas as requisições protegidas:
Authorization: Bearer <seu_token>

📦 Principais Entidades
EntidadeDescriçãoUsuariosCadastro e autenticação de usuáriosLivroGerenciamento do acervo de livrosEmprestimoControle de empréstimos

🧩 Funcionalidades

Cadastro e autenticação de usuários
Listagem, criação e gerenciamento de livros
Registro e controle de empréstimos
Segurança com filtro JWT em rotas protegidas


👨‍💻 Autor
Pedro Willian Rocha — @pedrowillianrocha68-stack



Sistema de biblioteca: Ler Bem
Integrantes: Pedro Willian Guimarães Rocha
             Jefferson José da Silva
             Davi Simões da Silva Nunes
# 📚 Sistema Biblioteca LerBem — Backend

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de uma biblioteca digital, com autenticação JWT, controle de usuários, livros e empréstimos.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security + JWT
- Maven
- JPA / Hibernate

---

## 📁 Estrutura do Projeto