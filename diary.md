# Diário

## 6 de Agosto de 2018

### [Tarefa 01](https://github.com/arielril/model-driven-soft-eng-class/issues/1)

#### Criação de um repositório

Na criação do repositório foi utilizado a plataforma de hospedagem de código-fonte com controle de versão **GitHub**. O repositório criado tem como nome *model-driven-soft-eng-class*, tem permissão de visualização pública e foi criado na minha conta pessoal.

[Clique aqui](https://github.com/arielril/model-driven-soft-eng-class) para acessar o repositório.

#### Quadro de tarefas

O quadro de tarefas foi criado utilizando uma funcionalidade do **GitHub** conhecida como **Projects**. Na criação do quadro de tarefas foi utilizado um template (*Automated Kanban*) para que o quadro de tarefas já realize automações baseado nas *issues* criadas no repositório.

#### Diário

Este é o diário requisitado na [Tarefa 01](https://github.com/arielril/model-driven-soft-eng-class/issues/1).

Este diário será organizado por datas de trabalho. Em cada descrição de data de trabalho será dada uma visão detalhada dos passos que foram necessários para completar a(s) tarefa(s) realizada(s) naquele dia.

O diário será escrito em Markdown para facilitar a descrição/apresentação das tarefas e para que seja possível visualizá-lo dentro do **GitHub** sem ter que sair da plataforma escolhida.


## 18 de Agosto de 2018

### [Tarefa 5](https://github.com/arielril/model-driven-soft-eng-class/issues/5)

#### Responder questionário 01

Para responder o questionário foi realizado uma leitura dos arquivos **Problems and opportunities.pdf** and **An introdution to Model Driven Architecture - Brown** presentes na pasta *important-files* na raiz do projeto.

As respostas entregues ao professor estão no arquivo **exercises5.md** na pasta *week3* na raiz do projeto.

## 22 de Agosto de 2018

Precisei instalar o SDK do astah para desenvolver um plugin referente ao Trabalho 1.

Para instalar criei uma função no arquivo de configuração do meu terminal (.zshrc) para que quando executada setasse as variáveis de ambiente necessárias para executar os programas do SDK.

```shell
set_astah_env() {
  export ASDK_HOME='/Users/arielril/Documents/8ºSemestre/eng-soft-model/repo/astah-plugin/astah-plugin-SDK-1.4';
  export PATH=$ASDK_HOME/bin:$PATH ;
}
```
E também foi preciso atribuir a variável de ambiente referente ao diretório do Java no meu computador.

```shell
export JAVA_HOME=<my_java_path>/jdk1.8.0_181.jdk/Contents/Home
```

Para setar as variáveis de ambiente é apenas preciso executar o comando `set_astah_env`. Depois de setar as variáveis de ambiente executei o comando `asdk` para validar se as variáveis de ambiente estavam corretas.

Em seguida criei um projeto para o plugin executando o seguinte comando `astah-generate-project`. Quando apareceu algo para atribuir nomes seguiu este exemplo

```
Define value for property 'groupId': : com.example
Define value for property 'artifactId': : eng-plugin
Define value for property 'version':  1.0-SNAPSHOT: :
Define value for property 'package':  com.example: :
Confirm properties configuration:
groupId: com.example
artifactId: eng-plugin
version: 1.0-SNAPSHOT
package: com.example
 Y: : y
```

Para fazer o build do projeto executei `astah-build`. Executou com sucesso!

## 27 de Agosto de 2018

Decidi realizar a implementação de um plugin para Eclipse.

Para criar a pasta do projeto do plugin foi necessário instalar o [Eclipse Plugin Development Environment](https://www.eclipse.org/pde/). Depois de instalar foi adicionado um menu a mais no momento de escolher o tipo do projeto a ser criado no Eclipse.

O plugin decidido a ser implementado foi um plugin que realiza a contagem dos métodos em um Workspace e diz quantas chamadas são feitas para cada método.

## 29 de Agosto de 2018

Durante a implementação do plugin foi descoberto um tutorial relacionado, o qual realiza a manipulação da árvore sintática.

[Clique aqui](https://www.programcreek.com/2012/06/count-total-number-of-methods-in-a-java-project/) para verificar o tutorial.

## 30 de Agosto de 2018

Foi realizado a adição do plugin dentro deste projeto e realizado a primeira release do plugin, a qual esta posicionada na parte de releases do projeto master.

[Clique aqui](https://github.com/arielril/model-driven-soft-eng-class/tree/master/org.plugin.method-counter) para verificar o repositório do plugin.
