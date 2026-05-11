# Contatos API

API REST para gerenciamento de contatos, construída com Kotlin + Spring Boot.

## Tecnologias

- Kotlin 2.2
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- H2 Database (em memória)
- Springdoc OpenAPI (Swagger UI)
- Gradle (Kotlin DSL)
- Java 21

## Pré-requisitos

- Java 21

## Executando projeto

### Windows

```bash
gradlew.bat bootRun
```

### Linux/macOS

```bash
./gradlew bootRun
```

## Endpoints

Base URL: `http://localhost:8080/api/contacts`

- `GET /api/contacts` - Lista todos os contatos
- `GET /api/contacts/{id}` - Busca contato por ID
- `POST /api/contacts` - Cria novo contato
- `PUT /api/contacts/{id}` - Atualiza contato existente
- `DELETE /api/contacts/{id}` - Remove contato

### Códigos de resposta (comportamento atual)

- `GET /api/contacts`: 200
- `GET /api/contacts/{id}`: 200 ou 404
- `POST /api/contacts`: 200
- `PUT /api/contacts/{id}`: 200 ou 404
- `DELETE /api/contacts/{id}`: 204 ou 404

## Modelo de contato

Exemplo de payload para criação/atualização:

```json
{
  "name": "Maria Souza",
  "email": "maria.souza@email.com",
  "phone": "11999998888",
  "birthDate": "1992-08-15",
  "cep": "01001000",
  "neighborhood": "Sé",
  "street": "Praça da Sé",
  "number": "100",
  "state": "SP",
  "city": "São Paulo"
}
```

Campos da entidade:

- `id` (Long, gerado automaticamente)
- `name` (String)
- `email` (String)
- `phone` (String)
- `birthDate` (LocalDate, formato `yyyy-MM-dd`)
- `cep` (String)
- `neighborhood` (String)
- `street` (String)
- `number` (String)
- `state` (String)
- `city` (String)

## Exemplo com cURL

Criar contato:

```bash
curl -X POST "http://localhost:8080/api/contacts" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Souza",
    "email": "maria.souza@email.com",
    "phone": "11999998888",
    "birthDate": "1992-08-15",
    "cep": "01001000",
    "neighborhood": "Sé",
    "street": "Praça da Sé",
    "number": "100",
    "state": "SP",
    "city": "São Paulo"
  }'
```

Listar contatos:

```bash
curl "http://localhost:8080/api/contacts"
```

## Testes

Executar os testes automatizados:

```bash
./gradlew test
```

No Windows:

```bash
gradlew.bat test
```

## Estrutura do projeto

```
contatos-api/
├── src/
│   ├── main/
│   │   ├── kotlin/dev/jefersonguerrajr/contatos_api/
│   │   │   ├── ContatosApiApplication.kt           # Classe principal da aplicação
│   │   │   ├── config/                             # Configurações
│   │   │   │   ├── SwaggerConfig.kt               # Configuração Swagger/OpenAPI
│   │   │   │   └── StartupInfoConfig.kt           # Configurações de inicialização
│   │   │   ├── controller/                        # Controladores REST
│   │   │   │   └── ContactController.kt           # Endpoints dos contatos
│   │   │   ├── service/                           # Lógica de negócio
│   │   │   │   └── ContactService.kt              # Serviço de contatos
│   │   │   ├── repository/                        # Persistência de dados
│   │   │   │   └── ContactRepository.kt           # Repositório de contatos
│   │   │   └── model/                             # Modelos de dados
│   │   │       └── Contact.kt                     # Entidade Contact
│   │   └── resources/
│   │       └── application.yaml                   # Configurações da aplicação
│   └── test/
│       └── kotlin/dev/jefersonguerrajr/contatos_api/
│           └── ContatosApiApplicationTests.kt     # Testes automatizados
├── build.gradle.kts                               # Configuração do build (Gradle)
├── settings.gradle.kts                            # Configurações do Gradle
├── gradlew                                        # Wrapper Gradle (Linux/macOS)
├── gradlew.bat                                    # Wrapper Gradle (Windows)
└── README.md                                      # Este arquivo
```
