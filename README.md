# Documentação

### **Documentação do Sistema de Cinema**

#### 1. **Introdução**

Este documento descreve o sistema de gerenciamento de um cinema, incluindo as funcionalidades, arquitetura e a implementação do backend utilizando bancos de dados SQL e NoSQL. O objetivo é fornecer uma solução robusta para gerenciar filmes, sessões, reservas, e preferências de usuários, utilizando uma arquitetura poliglota de persistência.

---

#### 2. **Análise de Requisitos**

##### **2.1. Requisitos Funcionais**

- **RF01**: O sistema deve permitir o cadastro de filmes, sessões e salas.
- **RF02**: O sistema deve permitir a reserva de ingressos para os usuários.
- **RF03**: O sistema deve permitir o gerenciamento de usuários (cadastro, login, logout).
- **RF04**: O sistema deve registrar logs de atividades dos usuários (ex.: login, reserva, avaliação de filmes).
- **RF05**: O sistema deve oferecer recomendações de filmes baseadas no histórico de visualização e preferências dos usuários.
- **RF06**: O sistema deve gerar relatórios de reservas e ocupação de salas.

##### **2.2. Requisitos Não Funcionais**

- **RNF01**: O sistema deve ser capaz de escalar horizontalmente para suportar um grande volume de usuários simultâneos.
- **RNF02**: O sistema deve garantir a consistência dos dados críticos, como reservas de ingressos, para evitar duplicidade de assentos.
- **RNF03**: O sistema deve ser flexível para lidar com mudanças no formato dos dados de preferências e comportamentos dos usuários.
- **RNF04**: O tempo de resposta deve ser inferior a 1 segundo para consultas de disponibilidade de ingressos.
- **RNF05**: O sistema deve ser compatível com os navegadores modernos e dispositivos móveis.

---

#### 3. **Casos de Uso**

##### **Caso de Uso 1: Cadastro de Filme**

**Ator**: Administrador  
**Fluxo Principal**:  

1. O administrador acessa a área de gerenciamento de filmes.
2. O sistema solicita as informações do filme (título, gênero, duração, classificação etária).
3. O administrador insere as informações e confirma.
4. O sistema salva os dados no banco SQL.

##### **Caso de Uso 2: Reserva de Ingressos**

**Ator**: Usuário  
**Fluxo Principal**:  

1. O usuário faz login no sistema.
2. O usuário busca por filmes ou sessões disponíveis.
3. O usuário escolhe uma sessão e seleciona o assento desejado.
4. O sistema verifica a disponibilidade do assento.
5. Se disponível, o sistema confirma a reserva e armazena no banco SQL.
6. O sistema registra um log da reserva no banco NoSQL.

##### **Caso de Uso 3: Recomendação de Filmes**

**Ator**: Usuário  
**Fluxo Principal**:  

1. O sistema analisa o histórico de visualizações e preferências do usuário armazenados no banco NoSQL.
2. O sistema gera uma lista de recomendações.
3. O usuário visualiza as recomendações na página inicial.
4. O sistema registra a interação no banco NoSQL.

---

#### 4. **Modelagem do Sistema**

##### **Diagrama de Casos de Uso**

Este diagrama apresenta os principais casos de uso do sistema de cinema:

```plaintext
+------------------------------------+
|           [Usuário]                |
+------------------------------------+
        |                        |
        V                        V
 [Reservar Ingressos]      [Receber Recomendações]
        |
        V
 [Verificar Disponibilidade]

+------------------------------------+
|         [Administrador]            |
+------------------------------------+
        |
        V
 [Gerenciar Filmes e Sessões]
```

---

#### 5. **Diagramas de Classes**

##### **5.1. Diagrama de Classes SQL**

Este diagrama ilustra as principais entidades do sistema que serão armazenadas no banco de dados relacional.

```plaintext
+---------------------------------+
|            Filme                |
+---------------------------------+
| - id: Long                      |
| - titulo: String                |
| - genero: String                |
| - duracao: int                  |
| - classificacaoEtaria: String   |
+---------------------------------+

+---------------------------------+
|            Sala                 |
+---------------------------------+
| - id: Long                      |
| - nome: String                  |
| - capacidade: int               |
+---------------------------------+

+---------------------------------+
|            Sessao               |
+---------------------------------+
| - id: Long                      |
| - horario: LocalDateTime        |
| - filme: Filme                  |
| - sala: Sala                    |
+---------------------------------+

+---------------------------------+
|            Reserva              |
+---------------------------------+
| - id: Long                      |
| - assento: int                  |
| - sessao: Sessao                |
| - usuario: Usuario              |
+---------------------------------+

+---------------------------------+
|            Usuario              |
+---------------------------------+
| - id: Long                      |
| - nome: String                  |
| - email: String                 |
| - senha: String                 |
+---------------------------------+
```

##### **5.2. Diagrama de Classes NoSQL**

Este diagrama representa as classes que serão armazenadas no banco de dados NoSQL (ex.: MongoDB).

```plaintext
+---------------------------------+
|         LogAtividade            |
+---------------------------------+
| - usuarioId: String             |
| - dataHora: LocalDateTime       |
| - acao: String                  |
| - detalhes: String              |
+---------------------------------+

+------------------------------------+
|      PreferenciasUsuario           |
+------------------------------------+
| - usuarioId: String                |
| - generosFavoritos: List<String>   |
| - filmesAssistidos: List<String>   |
| - avaliacoes: Map<String, Integer> |
+------------------------------------+

+------------------------------------+
|        Recomendacoes               |
+------------------------------------+
| - usuarioId: String                |
| - filmesRecomendados: List<String> |
+------------------------------------+
```

---

#### 6. **Arquitetura do Sistema**

##### **6.1. Arquitetura Geral**

A arquitetura do sistema é baseada em uma solução backend que utiliza o Spring Boot para construir APIs REST, integrando um banco de dados SQL (ex.: PostgreSQL) e um banco NoSQL (ex.: MongoDB). A comunicação entre as camadas é realizada por meio de serviços, que gerenciam a lógica de negócio e a persistência de dados.

- **Frontend**: Interface gráfica (pode ser em Angular, React, etc.) que consome as APIs REST do backend.
- **Backend**: Spring Boot, com serviços organizados para interação com SQL e NoSQL.
- **Banco de Dados**: PostgreSQL (SQL) e MongoDB (NoSQL).
- **Segurança**: Spring Security para autenticação e controle de acesso.

##### **6.2. Fluxo de Requisições**

1. O usuário interage com a interface gráfica (frontend).
2. O frontend faz chamadas HTTP para as APIs REST do backend.
3. O backend processa as requisições e interage com os bancos de dados conforme a necessidade (SQL para dados estruturados e NoSQL para dados flexíveis).
4. As respostas são retornadas ao frontend para exibição ao usuário.

---

#### 7. **Tecnologias Utilizadas**

- **Backend**: Java com Spring Boot
- **Banco de Dados Relacional**: PostgreSQL
- **Banco de Dados Não Relacional**: MongoDB
- **Segurança**: Spring Security para autenticação
- **Frontend**: React/Angular (não implementado nesta fase, mas previsto)
- **Ferramentas de Build**: Maven/Gradle

---

#### 8. **Conclusão**

Este sistema de cinema oferece uma solução híbrida de persistência, combinando bancos de dados SQL e NoSQL para maximizar a eficiência e escalabilidade. O modelo permite gerenciar dados críticos, como reservas e sessões, ao mesmo tempo que captura e analisa o comportamento dos usuários para oferecer uma experiência personalizada com recomendações de filmes.

---
