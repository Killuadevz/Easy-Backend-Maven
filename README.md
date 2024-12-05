# Easy - Backend (Java)

## 📚 **Projeto Integrador - SENAI**

Este projeto é parte do **Projeto Integrador do SENAI**, no qual os alunos foram desafiados a desenvolver uma solução real e funcional de forma autônoma, simulando um ambiente profissional. O objetivo é aplicar os conhecimentos adquiridos durante o curso em um sistema completo, incluindo programação, banco de dados, e boas práticas de desenvolvimento de software.

A professora **Fiama Brenda**, responsável pelos módulos de **Backend** e **Banco de Dados**, desafiou os alunos a implementarem uma *view* e uma *stored procedure* para o sistema, como forma de reforçar o uso de SQL avançado.

---

## 🧾 **Sobre o Projeto**

O **Easy** é um sistema de gerenciamento de contatos, desenvolvido para oferecer uma solução completa e funcional. Ele utiliza tecnologias modernas e segue boas práticas de programação, integrando diferentes camadas de uma aplicação backend. A proposta é criar um serviço RESTful que permita operações básicas e consultas avançadas sobre contatos.

Desenvolvido de forma autônoma, este projeto simula um ambiente de trabalho real, desde o planejamento até a implementação e documentação.

---

## 🚀 **Funcionalidades**

1. **Gerenciamento de Contatos**
   - Criar novo contato com nome, número, descrição e link para foto.
   - Buscar todos os contatos ou consultar um contato específico pelo seu ID.
   - Atualizar informações de contatos existentes.
   - Deletar contatos cadastrados.

2. **Consultas Avançadas** *(Desafio da professora Fiama Brenda)*
   - Total de contatos com números começando por "11", utilizando uma **view SQL**.
   - Total geral de contatos cadastrados, utilizando uma **stored procedure**.

3. **Configuração de CORS**
   - Permite integração com qualquer frontend ou aplicação externa.

---

## 🛠️ **Tecnologias Utilizadas**

### **Linguagem e Framework**
- **Java 17**: Linguagem principal para o desenvolvimento do backend.
- **Spring Boot**:
  - **Spring Web**: Para construção da API RESTful.
  - **Spring Data JPA**: Para integração com o banco de dados.
  - **JdbcTemplate**: Para consultas SQL customizadas.

### **Banco de Dados**
- **MySQL**: Banco de dados relacional utilizado para armazenamento de informações.
- *Views* e *Stored Procedures* foram implementadas como parte do desafio técnico proposto.

---

## 🚠 **Requisitos do Banco de Dados**

Para que o projeto funcione corretamente, é necessário configurar o banco de dados **MySQL** e criar a **view** e a **stored procedure** descritas abaixo.

### **Tabela Necessária**
O banco de dados deve conter a tabela `contatos` com os seguintes campos:

| Campo       | Tipo        | Descrição                           |
|-------------|-------------|-------------------------------------|
| `id`        | UUID        | Identificador único do contato.     |
| `nome`      | VARCHAR     | Nome do contato.                   |
| `numero`    | VARCHAR     | Número de telefone do contato.     |
| `descricao` | VARCHAR     | Descrição adicional do contato.    |
| `link`      | VARCHAR     | Link da foto do contato.           |

### **Criação da View**

A **view** `view_contatos_com_11` é utilizada para calcular o total de contatos que possuem "11" no número. Para criar a view, execute o seguinte comando no banco de dados:

```sql
CREATE VIEW view_contatos_com_11 AS
SELECT COUNT(*) AS total_contatos_com_11
FROM contatos
WHERE numero LIKE '%11%';
```

### **Criação da Stored Procedure**

A **stored procedure** `contar_contatos` é utilizada para calcular o total geral de contatos cadastrados. Para criá-la, execute o seguinte comando:

```sql
DELIMITER $$
CREATE PROCEDURE contar_contatos()
BEGIN
    SELECT COUNT(*) AS total_contatos
    FROM contatos;
END $$
DELIMITER ;
```
---


## 🗂️ **Estrutura do Projeto**

- **`controller/`**: Define os endpoints REST da aplicação.
- **`dto/`**: Contém os Data Transfer Objects, utilizados para transportar dados entre as camadas.
- **`model/`**: Modelos de dados mapeados para as tabelas do banco.
- **`repository/`**: Interfaces que facilitam as operações com o banco de dados usando JPA.
- **`service/`**: Contém a lógica de negócios, como consultas avançadas e integrações.
- **`webcontroller/`**: Configuração de CORS para a API.

---

## 📖 **Endpoints da API**

### **Gerenciamento de Contatos**

1. **Listar todos os contatos**
   - **Rota:** `GET /api`
   - **Descrição:** Retorna uma lista com todos os contatos cadastrados.
   - **Retorno:** 
     - Sucesso: Lista de contatos.
     - Erro: Mensagem informando que não há contatos cadastrados.

2. **Buscar contato por ID**
   - **Rota:** `GET /api/{id}`
   - **Parâmetro:** `id` (UUID do contato).
   - **Descrição:** Retorna os detalhes de um contato específico.
   - **Retorno:**
     - Sucesso: Detalhes do contato.
     - Erro: Mensagem informando que o contato não foi encontrado.

3. **Criar novo contato**
   - **Rota:** `POST /api/new`
   - **Descrição:** Cria um novo contato no sistema.
   - **Body (JSON):**
     ```json
     {
       "nome": "João Silva",
       "numero": "11987654321",
       "descricao": "Contato pessoal",
       "link": "https://link-da-foto.com/joao.jpg"
     }
     ```
     - **Campo `link`:** Representa o link da foto do contato (URL da imagem).

4. **Atualizar contato**
   - **Rota:** `PUT /api/{id}`
   - **Parâmetro:** `id` (UUID do contato).
   - **Descrição:** Atualiza as informações de um contato.
   - **Body (JSON):**
     ```json
     {
       "nome": "João Atualizado",
       "numero": "11999999999",
       "descricao": "Descrição atualizada",
       "link": "https://link-da-foto.com/joao-atualizado.jpg"
     }
     ```
     - **Campo `link`:** Representa o link atualizado da foto do contato (URL da imagem).

5. **Deletar contato**
   - **Rota:** `DELETE /api/dell/{id}`
   - **Parâmetro:** `id` (UUID do contato).
   - **Descrição:** Remove um contato do sistema.
   - **Retorno:**
     - Sucesso: Mensagem informando que o contato foi deletado.
     - Erro: Mensagem de erro.

### **Consultas Avançadas**

1. **Total de contatos com números começando por "11"**
   - **Rota:** `GET /api/view`
   - **Descrição:** Retorna o número de contatos que possuem números começando por "11", utilizando uma **view SQL**.
   - **Retorno:** 
     - Sucesso: Total de contatos com números começando por "11".
     - Erro: Mensagem de erro.

2. **Total geral de contatos**
   - **Rota:** `GET /api/procedure`
   - **Descrição:** Executa uma **stored procedure** para calcular o total de contatos cadastrados.
   - **Retorno:** 
     - Sucesso: Total de contatos.
     - Erro: Mensagem de erro.

---

## 🔌 **Como Configurar e Executar**

### **Pré-requisitos**
- **Java 21 ou superior** instalado.
- **MySQL** configurado.

### **Passos para Rodar**
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/easy-backend.git](https://github.com/Killuadevz/Easy-Backend-Maven/tree/main/demo
   cd Easy-Backend-Maven
   ```
2. Configure o arquivo `application.properties` com suas credenciais do banco de dados:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/easy
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Execute a aplicação com seu IDE preferido (como IntelliJ IDEA ou Eclipse) ou pelo terminal com:
   ```bash
   java -jar target/easy-backend.jar
   ```
4. Acesse a API na URL padrão: `http://localhost:8080/api`.

---

## 🗃️ **Banco de Dados**

### **Estrutura de Tabelas**
- **Tabela:** `contatos`
  - Campos: `id` (UUID), `nome`, `numero`, `descricao`, `link`.

### **Consultas Avançadas**
- **View:** `view_contatos_com_11`
  - Retorna o total de contatos com números começando por "11".
- **Stored Procedure:** `contar_contatos()`
  - Calcula o total geral de contatos cadastrados.

Essas implementações fazem parte do **desafio técnico proposto pela professora Fiama Brenda** para estimular a utilização de SQL avançado no projeto.

---

## 📌 **Considerações Finais**

O **Easy** é uma aplicação backend robusta, desenvolvida para consolidar conhecimentos e simular um ambiente de trabalho real. Ele oferece uma base sólida para gerenciamento de contatos e pode ser estendido para atender a demandas mais complexas.

Contribuições e melhorias são sempre bem-vindas!

---

## 📝 **Licença**

Este projeto está sob a licença **MIT**, permitindo que você o use, modifique e distribua livremente.

---

**Desenvolvido com ❤️ por (https://github.com/Killuadevz).**
