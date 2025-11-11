# 📚 Sistema CRUD Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em Java com funcionalidades de CRUD para livros e sistema de empréstimos.

## 🎯 Sobre o Projeto

O sistema foi desenvolvido para gerenciar o acervo de uma biblioteca que realiza empréstimos de livros, oferecendo funcionalidades completas de cadastro, consulta, atualização e exclusão de livros, além do controle de empréstimos e devoluções.

## ⚙️ Tecnologias Utilizadas

- **Java 24** - Linguagem principal
- **Maven** - Gerenciamento de dependências e build
- **Javalin** - Framework web para criação da API REST
- **Thymeleaf** - Template engine para páginas web
- **JUnit 5** - Testes unitários
- **JQwik** - Testes baseados em propriedades
- **Selenium** - Testes automatizados de interface
- **JaCoCo** - Cobertura de código
- **SpotBugs & Checkstyle** - Análise estática de código

## 📋 Regras de Negócio

### Gerenciamento de Livros
1. **Campos obrigatórios**: Cada livro deve ter título, autor e ISBN único de 13 dígitos
2. **Validação**: Nenhum campo pode ser nulo ou vazio
3. **Unicidade**: O acervo contém apenas um exemplar de cada livro
4. **Operações**: CRUD completo (Criar, Ler, Atualizar, Deletar)
5. **Consultas**: Busca por ID, título e autor

### Sistema de Empréstimos
1. **Registro**: Sistema registra data do empréstimo e data prevista para devolução
2. **Prazo gratuito**: 10 dias corridos a partir da data do empréstimo
3. **Multas**: Após o prazo, cobrança de `R$ 5,00` fixo + `R$ 0,50` por dia adicional
4. **Controle**: Registro de empréstimos, devoluções e consulta de livros emprestados

## 🚀 Como Executar

### Pré-requisitos
- Java 24 ou superior
- Maven 3.6+ 
- Git

### Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/Shyuu7/CRUD-Biblioteca
cd PB-CRUD-Biblioteca
```

2. **Instale as dependências**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn exec:java -Dexec.mainClass="com.br.infnet.app.Main"
```

4. **Acesse a aplicação**
```
http://localhost:7000
```

## 🧪 Testes

### Executar todos os testes
```bash
mvn test
```

### Executar apenas testes unitários
```bash
mvn test -Dtest="com.br.infnet.service.**.*Test"
```

### Executar testes Selenium
```bash
mvn test -Dtest="com.br.infnet.selenium.**.*Test"
```

### Gerar relatório de cobertura
```bash
mvn test jacoco:report
```

### Análise estática de código
```bash
# SpotBugs
mvn spotbugs:check

# Checkstyle
mvn checkstyle:check
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/br/infnet/
│   │       ├── app/           # Classe principal
│   │       ├── controller/    # Controladores REST
│   │       ├── model/         # Modelos de dados
│   │       ├── service/       # Lógica de negócio
│   │       ├── security/      # Validação e sanitização
│   │       ├── utils/         # Utilitários
│   │       └── view/          # Camada de apresentação
│   └── resources/
│       └── test-data/         # Dados para testes
└── test/
    ├── java/
    │   └── com/br/infnet/
    │       ├── selenium/      # Testes E2E
    │       └── service/       # Testes unitários
    └── resources/
        └── test-data/         # Dados de teste
```

## 🔒 Segurança

O sistema implementa múltiplas camadas de segurança:

- **Sanitização de entrada**: Prevenção contra XSS e injeção de código
- **Validação rigorosa**: Verificação de formato e conteúdo dos dados
- **Fail-safe**: Sistema falha de forma segura e controlada
- **Timeouts**: Proteção contra operações que consomem muitos recursos

## 📊 CI/CD

O projeto inclui workflows automatizados para:
- **Build** automatizado com Maven
- **Testes** unitários e de integração
- **Análise de qualidade** de código
- **Cobertura** de testes
- **Testes E2E** com Selenium

## 🎮 Funcionalidades

### API Endpoints

#### Livros
- `GET /livros` - Listar todos os livros
- `GET /livros/{id}` - Buscar livro por ID
- `POST /livros` - Cadastrar novo livro
- `PUT /livros/{id}` - Atualizar livro
- `DELETE /livros/{id}` - Excluir livro

#### Empréstimos
- `POST /emprestimos` - Registrar empréstimo
- `PUT /emprestimos/{id}/devolucao` - Registrar devolução
- `GET /emprestimos` - Listar empréstimos ativos
- `GET /emprestimos/{id}/multa` - Calcular multa

### Interface Web
- Formulários para cadastro e edição de livros
- Listagem paginada do acervo
- Sistema de busca por título, autor ou ISBN
- Controle de empréstimos e devoluções
- Cálculo automático de multas

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 👥 Autores

- **Desenvolvedor Principal** - [Larissa Conti](https://github.com/Shyuu7)