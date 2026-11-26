# Sistema de Gestão Clínica

Victor Souza Perez

O projeto foi desenvolvido individualmente.

===================== Tema escolhido =====================

Sistema de gerenciamento de uma clínica médica.

Projeto desenvolvido durante o curso de Análise e Desenvolvimento de Sistemas, na disciplina de Programação Orientada a Objetos.

===================== Descrição do projeto =====================

O sistema tem como objetivo auxiliar no gerenciamento de uma clínica médica, possibilitando o cadastro de médicos e pacientes, além do gerenciamento dos atendimentos realizados pela clínica.

===================== Funcionalidades =====================

- Cadastro, consulta, alteração e exclusão de médicos.
- Cadastro, consulta, alteração e exclusão de pacientes.
- Gerenciamento e agendamento de atendimentos.
- Relacionamento entre médicos, pacientes e atendimentos.
- Validação dos campos dos formulários.
- Alteração e exclusão de registros diretamente pela tabela.
- Armazenamento das informações no banco de dados.

===================== Entidades =====================

- Pessoa
- Médico
- Paciente
- Atendimento

O sistema possui três entidades principais: Médico, Paciente e Atendimento.

As entidades Médico e Paciente herdam da classe Pessoa, que possui os atributos em comum entre elas. A entidade Atendimento é responsável por relacionar um médico e um paciente, armazenando também a data e o horário da consulta.

===================== Tecnologias utilizadas =====================

- Java
- JavaFX
- MySQL
- JDBC
- Gradle

===================== Estrutura do projeto =====================

O projeto foi desenvolvido utilizando a arquitetura BCE (Boundary, Control e Entity), juntamente com o padrão DAO para a persistência dos dados.

edu.curso
├── View
├── Controller
├── Entity
└── DAO


A comunicação entre as camadas View e Controller é realizada através de Properties e Bindings do JavaFX. Para a atualização das tabelas, foi utilizado ObservableList.

A camada DAO possui interfaces e suas respectivas implementações, responsáveis pela comunicação com o banco de dados através do JDBC.

===================== Como executar =====================

Para executar o projeto, é necessário possuir o Java e o MySQL instalados.

Crie o banco de dados `clinica` com suas respectivas tabelas e configure as informações de conexão nas classes de implementação do DAO.

Após realizar as configurações, execute o seguinte comando no terminal, dentro da pasta do projeto:

bash>>>>>
./gradlew build
./gradlew run


===================== Divisão de responsabilidades =====================

Como o projeto foi desenvolvido individualmente, todas as etapas ficaram sob minha responsabilidade.

- Modelagem das entidades.
- Modelagem do banco de dados.
- Desenvolvimento dos três CRUDs.
- Desenvolvimento das interfaces gráficas.
- Implementação das camadas de controle e DAO.
- Integração com o banco de dados.
- Testes e validações do sistema.

===================== Demonstração =====================

Vídeo demonstrando o funcionamento do sistema:

[Link do vídeo no YouTube]