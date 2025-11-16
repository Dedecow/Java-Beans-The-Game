Java Beans - The Game ☕

O "Java Beans - The Game" é um jogo de simulação de cafeteria desenvolvido em Java (Swing) que combina diversão e rigor acadêmico em sua construção. O jogador assume o papel de um barista, com o objetivo principal de proporcionar uma experiência lúdica e divertida para entusiastas de café, permitindo-lhes atender clientes (NPCs) com diferentes personalidades, explorar ingredientes e aprender receitas de forma interativa.

Academicamente, o projeto serve como um estudo de caso prático para a aplicação rigorosa de princípios de Orientação a Objetos (Herança, Polimorfismo, Interfaces) e padrões de arquitetura de software, como MVC (Model-View-Controller) e DAO (Data Access Object), garantindo um código limpo, desacoplado e de alta manutenibilidade.

Funcionalidades Principais
O jogo conta com um Sistema de Clientes (NPCs) que gera personagens com 4 personalidades distintas (Apressado, Calmo, Exigente, Indeciso). Cada cliente possui um nome e uma frase únicos, lidos diretamente do banco de dados, o que agrega variedade e imprevisibilidade ao gameplay.

O Cardápio Dinâmico e os ingredientes são totalmente carregados do banco de dados MySQL, permitindo fácil expansão e personalização. Na Mecânica de Jogo, o jogador deve preparar o pedido correto na TelaPreparo, sendo que consultar o livro (TelaReceita) para verificar a receita penaliza a pontuação final da rodada, adicionando uma camada estratégica.

Ao final da partida (TelaGameOver), a pontuação do jogador (definido na TelaInicial) é salva na tabela historico do MySQL, implementando a Persistência de Ranking. O projeto foi desenvolvido de forma colaborativa, com foco em uma Arquitetura Profissional e assistência de IA, resultando em um código bem estruturado e documentado.

🛠️ Tecnologias Utilizadas
Linguagem Principal: Java (JDK 17+)

Interface Gráfica (View): Java Swing

Banco de Dados (Data): MySQL Server

Conectividade (Persistence): JDBC (via mysql-connector-j)

Arquitetura: Padrão MVC (Model-View-Controller) e DAO (Data Access Object)

IDE: Apache NetBeans

🏗️ Arquitetura do Sistema (MVC)
O projeto segue um padrão rigoroso de Separação de Responsabilidades, organizado em três camadas principais:

view (A Interface): Orquestrada pelo MainUI.java (o JFrame principal), onde todas as telas são JPanels modulares (ex: TelaInicial, TelaJogo). A view é "burra": ela apenas exibe dados e envia comandos de ação para o engine, nunca acessando o banco de dados diretamente.

engine (O Controlador): Representado pelo Jogo.java, atua como o "maestro" do jogo. Ele controla o estado da partida (pontuação, jogador atual) e é a única camada que pode se comunicar tanto com a view quanto com a data.

data (O Modelo e os DAOs): Divide-se em:

model: Contém os "JavaBeans" (as classes de entidade, como Cliente, MenuItem, Historico).

persistence: Contém os DAOs (Data Access Object), como HistoricoDAOMySQL (gerencia a pontuação), CardapioDAOMySQL, ClienteNpcDAO e FrasesDAO (gerenciam a leitura do conteúdo estático do jogo).

setup: Contém classes "Factory" como ClienteGen, que utilizam os DAOs para montar novos objetos de forma dinâmica.

🚀 Como Executar o Projeto (Guia de Instalação)
Para executar o projeto em sua máquina local, siga estes 4 passos:

Configurar o Banco de Dados MySQL: Crie o schema javabeans_game e execute o arquivo script-para-bd.sql (localizado na raiz do projeto) para criar e popular todas as tabelas necessárias.

Adicionar o Driver JDBC ao NetBeans: Baixe o MySQL Connector/J e adicione o arquivo .jar às Libraries do projeto no NetBeans.

Criar o Arquivo de Credenciais (config.properties): Na pasta raiz do projeto, crie um arquivo com este nome e o seguinte conteúdo, substituindo pelas suas credenciais do MySQL:

properties
db.url=jdbc:mysql://localhost:3306/javabeans_game?useSSL=false
db.user=seu_usuario_mysql
db.pass=sua_senha_mysql
Executar o Jogo: No NetBeans, realize um "Clean and Build" e execute o projeto (ou o arquivo app.Cafeteria.java diretamente).

👥 Autores
Este projeto foi desenvolvido por André Ricardo S. Silveira (UNIFACS), Debora Cristina Erhart (UNISUL), Leonan Silva dos Santos (UNIFACS), Luísa Viotto Brandão (UAM) e Patrick Uriel Ferreira Miranda (SÃO JUDAS), sob a orientação do Professor Dr. Leandro Procopio Alves.
