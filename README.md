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
- **Docker** - Containerização da aplicação
- **CodeQL** - Análise de segurança estática
- **Snyk** - Análise de vulnerabilidades em dependências
- **OWASP ZAP** - Análise dinâmica de segurança
- **GitHub Actions** - CI/CD e automação

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
- Java 21 ou superior
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

### Gerar relatório HTML dos testes
```bash
mvn surefire-report:report
mvn site -DgenerateReports=false
```

### Gerar relatório de cobertura
```bash
mvn test jacoco:report
```

### Análise estática de código
```bash
mvn spotbugs:check

mvn checkstyle:check
```

## 🐳 Executar com Docker

### Usando imagem do DockerHub

1. **Pull e execute diretamente**
```bash
docker run -d -p 7000:7000 --name biblioteca-app shyuu7/crud-biblioteca:latest
```

2. **Acesse a aplicação**
```bash
http://localhost:7000
```

3. **Verifique os logs**
```bash
docker logs -f biblioteca-app
```

4. **Pare e remova o container**
```bash
docker stop biblioteca-app
docker rm biblioteca-app
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/br/infnet/
│   │       ├── app/          # Classe principal
│   │       ├── controller/   # Controladores REST
│   │       ├── model/        # Modelos de dados
|   |       ├── repository/
|   |       │   ├── implementations   # Implementações dos repositórios
|   |       │   └── interfaces        # Interfaces
│   │       ├── service/      # Lógica de negócio
│   │       ├── security/     # Validação e sanitização
│   │       ├── utils/        # Utilitários
│   │       └── view/         # Camada de apresentação
│   └── resources/
│       └── test-data/        # Dados para testes
└── test/
    ├── java/
    │   └── com/br/infnet/
    |       ├── security/      # Testes de segurança
    │       ├── selenium/      # Testes E2E
    │       └── service/       # Testes unitários
    └── resources/
        └── test-data/         # Dados de teste
target/
├── selenium-screenshots/        # Screenshots dos testes Selenium
└── site/
    ├── surefire-report.html      # Relatório HTML dos testes
    └── jacoco/                   # Relatório de cobertura JaCoCo
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

### 🔄 Workflows Automatizados

#### 1. Pipeline CI/CD (`pipeline-cd-ci.yaml`)

**Descrição**: Workflow principal de integração e entrega contínua que executa build e testes unitários, além da criação
de uma imagem Docker e seu push para o DockerHub.

**Triggers**:
- Push nas branches `main`, `prod`
- Pull requests para branches `main`, `prod`
- Execução manual via `workflow_dispatch`

**Funcionalidades**:
- ✅ Checkout do código fonte
- ☕ Configuração do ambiente Java 21
- 📦 Cache das dependências Maven
- 🔨 Build do projeto com Maven
- 🧪 Execução de testes unitários
- 📊 Geração de relatórios de testes
- 🐳 Construção da imagem Docker
- 📤 Push da imagem para o DockerHub
- 📁 Relatório JaCoCo e arquivo .jar salvos como artefatos diretamente no GitHub

#### 2. Análise de Qualidade do Código (`qualidade-codigo.yaml`)

**Descrição**: Workflow dedicado à análise estática e qualidade do código com múltiplas ferramentas.

**Triggers**:
- Execução completa do workflow `pipeline-cd-ci.yaml`
- Push na branch `dev`

**Funcionalidades**:
- 🔍 **Checkstyle**: Verificação de padrões de codificação
- 🐛 **SpotBugs**: Detecção de bugs potenciais com saída SARIF
- 🛡️ **GitHub Security**: Upload automático de resultados de segurança
- 📋 Relatórios exportados como artefatos diretamente no GitHub
- 💾 Cache otimizado para Maven

#### 3. Testes Selenium (`testes-selenium-workflow.yaml`)

**Descrição**: Workflow especializado em testes end-to-end automatizados com Selenium.

**Triggers**:
- Execução completa do workflow `pipeline-cd-ci.yaml`
- **Agendamento**: Segundas-feiras às 6:00 AM (cron: `0 6 * * 1`)

**Funcionalidades**:
- 🖥️ **Ambiente Virtual**: Configuração do Xvfb para testes headless
- 🌐 **Chrome Browser**: Instalação e configuração do Chrome estável
- 📥 **Docker**: Download e execução da imagem Docker criada no pipeline principal
- 🧪 **Testes E2E**: Execução dos testes Selenium
- 📸 **Screenshots**: Captura automática de evidências em caso de falha, salvas em `target/selenium-screenshots/`
- 📊 **Artefatos**: Upload dos resultados e evidências como artefatos no GitHub

#### 4. Pipeline Segurança e Análise (`analise-seguranca.yml`)

**Descrição**: Workflow abrangente de análise de segurança com múltiplas ferramentas SAST e DAST.

**Triggers**:
- Execução completa do workflow `pipeline-cd-ci.yaml`
- Push na branch `dev`

**Funcionalidades**:
- 🔒 **SAST com CodeQL**: Análise estática de segurança do código fonte
- 🔍 **Análise de Dependências**: Scanner Snyk para vulnerabilidades em dependências
- 🌐 **DAST com OWASP ZAP**: Testes dinâmicos de segurança na aplicação em execução
- 📊 **Relatórios SARIF**: Upload automático para GitHub Security
- 🚀 **Deploy Automatizado**: Staging e produção com aprovação manual
- 📋 **Relatórios HTML**: Geração de relatórios detalhados do Snyk
- 🐳 **Integração Docker**: Testes em ambiente containerizado


### 🔧 Configurações dos Workflows

**Otimizações Implementadas**:
- 📦 Cache das dependências Maven para builds mais rápidos
- ⏱️ Timeouts configurados para evitar builds infinitos
- 🎯 Execução condicional baseada em sucesso/falha
- 📊 Relatórios sempre gerados, mesmo em caso de falha

### 📈 Monitoramento e Relatórios

**Artefatos Gerados**:
- Arquivos .jar da aplicação das branches `main` e `prod`
- Relatórios de testes unitários (JUnit XML)
- Screenshots dos testes Selenium em `target/selenium-screenshots/`
- Relatórios de cobertura JaCoCo
- Resultados do Checkstyle e SpotBugs
- Análises de segurança SARIF (CodeQL e Snyk)
- Relatórios HTML de vulnerabilidades (Snyk)
- Resultados de testes DAST (OWASP ZAP)

**Integração Externa**:
- **GitHub Security**: Alertas de segurança automatizados
- **DockerHub**: Deploy de imagens containerizadas
- **OWASP ZAP**: Análise dinâmica de segurança
- **Snyk**: Monitoramento contínuo de vulnerabilidades

### 📋 Relatórios e Artefatos

Os workflows geram automaticamente:
- **Relatórios de Cobertura**: Disponíveis na aba "Actions" → "Análise de Qualidade"
- **Resultados de Testes**: Visualizáveis diretamente nas execuções dos workflows
- **Screenshots de Falhas**: Capturados automaticamente nos testes Selenium
- **Análises de Segurança**: Integradas ao GitHub Security

## 📊 Status dos Workflows

[![Pipeline CI/CD](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/pipeline-cd-ci.yaml/badge.svg)](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/pipeline-cd-ci.yaml)
[![Análise de Qualidade](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/qualidade-codigo.yaml/badge.svg)](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/qualidade-codigo.yaml)
[![Testes Selenium](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/testes-selenium-workflow.yaml/badge.svg)](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/testes-selenium-workflow.yaml)
[![Segurança](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/analise-seguranca.yml/badge.svg)](https://github.com/Shyuu7/CRUD-Biblioteca/actions/workflows/analise-seguranca.yml)

## 🎮 Funcionalidades

### API Endpoints

#### Livros
- `GET /livros` - Listar todos os livros
- `GET /livros/novo` - Exibir formulário de cadastro
- `POST /livros` - Cadastrar novo livro
- `GET /livros/{id}/editar` - Exibir formulário de edição
- `POST /livros/{id}/editar` - Atualizar livro
- `POST /livros/{id}/remover` - Remover livro
- `GET /buscar` - Buscar livros por título, autor ou ISBN

#### Empréstimos
- `GET /emprestimos` - Listar todos os empréstimos
- `GET /emprestimos/livros/{id}/emprestar` - Exibir formulário de empréstimo
- `POST /emprestimos/livros/{id}/emprestar` - Registrar empréstimo
- `POST /emprestimos/livros/{id}/devolver` - Registrar devolução

### Interface Web
- Formulários para cadastro e edição de livros
- Listagem paginada do acervo
- Sistema de busca por título, autor ou ISBN
- Controle de empréstimos e devoluções
- Cálculo automático de multas

## 👥 Autores

- **Desenvolvedora Principal** - [Larissa Conti](https://github.com/Shyuu7)
