# ETAPA 1 DA A3 - UC DUAL: PROGRAMAÇÃO DE SOLUÇÕES COMPUTACIONAIS - PRESENCIAL 2025-2

**Equipe:**
Luísa Viotto Brandão - Análise e Desenvolvimento de Sistemas - UAM
Patrick Uriel Ferreira Miranda - Ciências da Computação - SÂO JUDAS
André Ricardo S. Silveira - Análise e Desenvolvimento de Sistemas - UNIFACS
Lucas Bastos Pita Lima - Análise e Desenvolvimento de Sistemas - UNIFACS
Leonan Silva dos Santos - Análise e Desenvolvimento de Sistemas - UNIFACS
Debora Cristina Erhart - Banco de dados - UNISUL
Bianca Azevedo Zinani - Ciências da Computação - UAM

## INTRODUÇÃO E DEFINIÇÃO DA PROPOSTA

Na primeira etapa do projeto da UC Dual de Programação de Soluções Computacionais, foi realizada a apresentação formal da proposta de desenvolvimento de uma solução utilizando a linguagem Java com Paradigma de Orientação a Objetos. Esta fase inicial teve como objetivo principal estabelecer os fundamentos do projeto, compreender os requisitos necessários e organizar a estrutura de trabalho colaborativo.

## OBJETIVOS ESTABELECIDOS

Foram definidos três objetivos principais para esta etapa inicial:
1. Compreensão detalhada da proposta de projeto a ser desenvolvida
2. Identificação e análise dos requisitos técnicos necessários
3. Criação e configuração do repositório Git/GitHub para versionamento e colaboração

## ESCOPO DO PROJETO: JAVA BEANS - THE GAME

Foi definido o desenvolvimento do jogo "Java Beans - The Game", uma simulação de cafeteria com as seguintes características principais:

### Conceito do Jogo:
JavaBeans é um jogo dinâmico que simula o ambiente de uma cafeteria, onde o jogador aprende receitas e atende clientes em um fluxo contínuo de serviço.

### Mecânicas Básicas:
- **Menu Inicial**: Tela de início do jogo com botão "Start"
- **Sistema de Clientes**: 
  - Clientes com comportamentos distintos (extensões da classe Cliente)
  - Animação de chegada do cliente até o balcão
  - Sistema de pedidos personalizados
- **Sistema de Preparo**: 
  - Menu de bebidas com opções e ingredientes
  - Mecânica de seleção e combinação de ingredientes
  - Verificação de acerto/erro no preparo
- **Fluxo de Jogo**: 
  - Pedido correto → Próximo cliente
  - Pedido errado → Explicação do erro e oportunidade de corrigir

### Sistema de Feedback e Progresso:
- Tela de resultados com informações de sucesso/erro
- Explicação detalhada dos erros cometidos
- Sistema de pontuação baseado na eficiência e precisão
- Botões de controle: finalizar e chamar próximo cliente

### Expansões Futuras:
- Implementação de cronômetro (10 minutos para produção de 50 bebidas)
- Adição de variedade de sabores e complexidade nas receitas
- Sistema de níveis e dificuldade progressiva

## ETAPA 2: PLANEJAMENTO DETALHADO E DEFINIÇÃO TÉCNICA

### Arquitetura de Classes:
- **Classe Base Cliente**: Com atributos e comportamentos comuns
- **Classes Especializadas**: Diferentes tipos de clientes (Apressado, Exigente, Casual) como extensões da classe base
- **Sistema de Ingredientes**: Catálogo completo de ingredientes disponíveis para preparo
- **Sistema de Receitas**: Combinações específicas de ingredientes para cada bebida

### Fluxograma do Jogo:
1. Início → Tela Start
2. Cliente chega (vídeo aleatório)
3. Cliente faz pedido no balcão
4. Jogador acessa menu de bebidas
5. Seleção e preparo dos ingredientes
6. Validação do pedido
7. Feedback (Sucesso/Erro)
8. Pontuação e explicação de erros
9. Decisão: Próximo cliente ou Finalizar

## ETAPA 3: DESENVOLVIMENTO E IMPLEMENTAÇÃO

### Divisão de Tarefas por Especialidade:

#### **Backend e Lógica do Jogo:**
- **Responsáveis:** Patrick Uriel Ferreira Miranda, Leonan Silva dos Santos, Lucas Bastos Pita Lima
- **Atribuições:**
  - Implementação da engine principal do jogo (`Jogo.java`)
  - Desenvolvimento do sistema de clientes e seus comportamentos
  - Criação do sistema de cardápio e ingredientes
  - Implementação da lógica de pontuação e progressão
  - Desenvolvimento dos algoritmos de geração aleatória de clientes e pedidos

#### **Interface Gráfica (Telas):**
- **Responsáveis:** Luísa Viotto Brandão, André Ricardo S. Silveira
- **Atribuições:**
  - Desenvolvimento do orquestrador principal (`MainUI.java`)
  - Implementação das telas: Inicial, Jogo e Game Over
  - Criação do sistema de navegação entre telas
  - Design e implementação do tema visual retrô cafeteria
  - Desenvolvimento dos componentes de interface do usuário

#### **Persistência de Dados:**
- **Responsáveis:** Bianca Azevedo Zinani, Debora Cristina Erhart
- **Atribuições:**
  - Implementação do sistema de histórico de partidas
  - Desenvolvimento da interface de persistência (`IPersistencia.java`)
  - Criação do DAO para histórico (`HistoricoDAO.java`)
  - Configuração da conexão com banco de dados SQLite
  - Implementação do padrão de persistência para futura migração para MySQL

### Arquitetura Técnica Implementada:
- **Padrão MVC (Model-View-Controller)** com separação clara de responsabilidades
- **Injeção de Dependência** entre a engine do jogo e a interface gráfica
- **Factory Method** para criação dinâmica de telas
- **Padrão DAO (Data Access Object)** para abstração da persistência
- **Sistema de Enumeração** para controle de estados e navegação

## FERRAMENTAS E PLATAFORMAS

Foi estabelecido o uso do GitHub como plataforma de versionamento e colaboração, com a criação do repositório oficial: https://github.com/Dedecow/Java-Beans-The-Game

## METODOLOGIA DE TRABALHO

Enfatizou-se a importância do trabalho colaborativo em grupo, valorizando a diversidade de opiniões, habilidades e formações acadêmicas dos integrantes para enriquecer a experiência de aprendizagem e a qualidade do produto final. A abordagem orientada a objetos permitirá a criação de um sistema modular e expansível.

## PRÓXIMAS ETAPAS

[Espaço reservado para descrição da ETAPA 4]

[Espaço reservado para descrição da ETAPA 5]

[Espaço reservado para descrição da ETAPA 6]

[Espaço reservado para descrição da ETAPA 7]

## CONSIDERAÇÕES FINAIS

A Etapa 1 estabeleceu com sucesso os alicerces do projeto, definindo claramente o escopo, os objetivos e a estrutura de trabalho colaborativo. A criação do repositório GitHub e a definição do escopo do jogo "Java Beans - The Game" proporcionaram a base sólida necessária para o desenvolvimento das fases subsequentes do projeto. 

O planejamento da Etapa 2 detalhou a arquitetura técnica e o fluxo completo do jogo, preparando o terreno para a implementação. Na Etapa 3, atualmente em andamento, a divisão estratégica por especialidades está permitindo um desenvolvimento paralelo e eficiente dos diferentes componentes do sistema, com cada membro da equipe contribuindo conforme suas competências específicas.

**Data de Conclusão da Etapa 1:** Outubro de 2025
**Status da Etapa 3:** Em Desenvolvimento Ativo
