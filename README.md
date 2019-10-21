## GitHub Sample

![](https://github.com/roger8b/GitHubSample/workflows/Android%20CI/badge.svg)

 Objetivo deste projeto é demonstrar a utilização de algumas ferramentas de construção de aplicativos para plataforma android.

### Características do projeto:

**SDK**
- Sdk Alvo: 29
- Sdk Minimo: 19

**Linguagem:** 
- Kotlin

**Arquiterura:** 

- A camada de apresentação foi utilizado MVVM.
- Para as camadas de dados foi utilizado as camadas
	- UseCases : Classes com as açoes que o usuário pode executar
	- Domain : Classe de modelo do negócio.
	- Data: Classes de modelo do serviço e classes responsáveis por solicitar as informações ao serviço.

**Principais Bibliotecas:**

- Coroutines - Para as chamadas assíncronas;
- Retrofit - Chamadas do serviço;
- Navigation - Navegação e grafo de telas do app;
- Timber - Logs
- Arrow - Programação funcional;
- Coil - Tratamento e download de imagens;
- Jacoco - Gerar os relatorios de cobertura dos testes unitários;

### Informações adicionais
- Para gerar um relatorio de cobertura de testes executar o seguinte comando
``` ./gradlew jacocoTestReport ```

### Imagens do App

| Home Louder | Home Lista |
|-------------|------------|
|![Home Louder](https://github.com/roger8b/GitHubSample/blob/develop/readme_assets/home_louder.png)|![Home List](https://github.com/roger8b/GitHubSample/blob/develop/readme_assets/home_list.png)|

### Mapa Mental do App
![Mapa Mental](https://github.com/roger8b/GitHubSample/blob/develop/readme_assets/map_v1.png)

 

 

 

 

 
