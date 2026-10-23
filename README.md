# 🚀 Task Manager API

API REST para gerenciamento de tarefas desenvolvida com **Java 17**, **Spring Boot 3**, **Spring Data JPA**, **Bean Validation** e **OpenAPI (Swagger)**.

Este projeto nasceu a partir da evolução de uma API bancária desenvolvida durante a formação da DIO, sendo adaptado para o domínio de gerenciamento de tarefas e enriquecido com validações, filtros, documentação automática, migrações de banco e testes.

---

## 📖 Sobre o Projeto

O objetivo desta aplicação é fornecer uma API simples, escalável e bem estruturada para gerenciamento de tarefas, aplicando boas práticas amplamente utilizadas no mercado.

Os principais conceitos utilizados foram:

- Arquitetura em Camadas
- Spring Boot 3
- Spring Data JPA
- Bean Validation
- OpenAPI / Swagger
- Flyway Migration
- PostgreSQL
- Railway

---

## 🏗️ Arquitetura

A aplicação segue uma arquitetura baseada em camadas:

```text
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
Database
```

### Estrutura do Projeto

```text
src
├── controller
│   ├── TaskController
│   └── dto
│
├── service
│   ├── TaskService
│   └── TaskServiceImpl
│
├── domain
│   ├── model
│   ├── repository
│   └── enums
│
├── exception
│
└── resources
```

---

## ✅ Funcionalidades

### Gerenciamento de Tarefas

- Criar tarefas
- Buscar tarefa por ID
- Listar tarefas
- Atualizar tarefas
- Excluir tarefas
- Concluir tarefas

### Filtros

- Filtrar por Status
- Filtrar por Prioridade
- Filtrar por Status e Prioridade simultaneamente

### Validações

- Título obrigatório
- Limite de 100 caracteres para título
- Limite de 500 caracteres para descrição

---

## 📋 Modelo de Tarefa

```json
{
  "id": 1,
  "title": "Preparar apresentação",
  "description": "Montar apresentação para reunião do projeto",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "createdAt": "2026-10-23T10:00:00",
  "updatedAt": "2026-10-23T14:00:00"
}
```

---

## 🏷️ Status

```text
PENDING
IN_PROGRESS
COMPLETED
```

---

## 🎯 Prioridades

```text
LOW
MEDIUM
HIGH
```

---

# 🔗 Endpoints

## Criar Tarefa

```http
POST /tasks
```

### Exemplo

```json
{
  "title": "Preparar proposta",
  "description": "Elaborar escopo e estimativa",
  "priority": "HIGH"
}
```

---

## Listar Tarefas

```http
GET /tasks
```

---

## Buscar Tarefa

```http
GET /tasks/{id}
```

---

## Atualizar Tarefa

```http
PUT /tasks/{id}
```

### Exemplo

```json
{
  "title": "Preparar proposta",
  "description": "Adicionar cronograma detalhado",
  "status": "IN_PROGRESS",
  "priority": "HIGH"
}
```

---

## Excluir Tarefa

```http
DELETE /tasks/{id}
```

---

## Concluir Tarefa

```http
PATCH /tasks/{id}/complete
```

---

## Filtrar por Status

```http
GET /tasks?status=PENDING
```

---

## Filtrar por Prioridade

```http
GET /tasks?priority=HIGH
```

---

## Filtrar por Status e Prioridade

```http
GET /tasks?status=IN_PROGRESS&priority=HIGH
```

---

# 🛡️ Tratamento de Erros

A API possui tratamento global de exceções para:

- Tarefa não encontrada (404)
- Dados inválidos (400)
- Erros internos (500)

Exemplo:

```json
{
  "timestamp": "2026-10-23T15:30:00",
  "status": 404,
  "message": "Task not found"
}
```

---

# 📚 Documentação Swagger

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

Documentação OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

# 🗄️ Banco de Dados

Ambiente de desenvolvimento:

```text
H2 Database
```

Ambiente de produção:

```text
PostgreSQL
```

As migrações são gerenciadas através do:

```text
Flyway
```

---

# ▶️ Executando Localmente

### Clonar o repositório

```bash
git clone https://github.com/enzo-bf/task-manager-api.git
```

### Entrar na pasta

```bash
cd task-manager-api
```

### Executar

Linux/macOS:

```bash
./gradlew bootRun
```

Windows:

```powershell
.\gradlew.bat bootRun
```

---

# ☁️ Deploy no Railway

Variáveis necessárias:

```env
SPRING_PROFILES_ACTIVE=prd

PGHOST=
PGPORT=
PGDATABASE=
PGUSER=
PGPASSWORD=
```

O Railway utilizará automaticamente a variável:

```env
PORT
```

para exposição da aplicação.

---

# 🧪 Testes

Para executar os testes:

Linux/macOS:

```bash
./gradlew test
```

Windows:

```powershell
.\gradlew.bat test
```

---

# 🚀 Evoluções Implementadas

Além do projeto original, foram adicionados:

- ✅ Novo domínio de negócio (Task Manager)
- ✅ DTOs dedicados
- ✅ Bean Validation
- ✅ Filtros por Status
- ✅ Filtros por Prioridade
- ✅ Swagger/OpenAPI
- ✅ Flyway Migration
- ✅ Tratamento global de exceções
- ✅ Testes unitários
- ✅ Suporte ao Railway

---

# 👨‍💻 Autor

**Enzo Francisco**

Desenvolvedor Back-End focado em:

- Java
- Spring Boot
- Microsserviços
- Cloud Computing
- Arquitetura de Software

---

## ⭐ Projeto desenvolvido durante a formação da Digital Innovation One (DIO)
