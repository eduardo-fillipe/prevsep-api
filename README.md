

# PrevSep+ Web API
Backend da aplicação PrevSep. Desenvolvida em parceria com o Hospital Universitário da Universidade Federal de Sergipe (HU-UFS).

![image](https://user-images.githubusercontent.com/35767060/121809107-e86f3c80-cc31-11eb-80f3-682996d7f3a9.png)

## Tutorial de uso
### Ferramentas necessárias
- Conexão à internet
- Java Develompent Kit versão 11 (ou superior)
- Maven 3.6.3  (ou superior)
- IDE Java (IntelliJ, preferencialmente) (Opcional)
### Compilando a aplicação
Após fazer o download o repositório, executar o seguinte comando no terminal dentro do diretório da aplicação:
>**$  mvn clean package**
### Executando a aplicação
Uma vez finalizada a compilação, existem duas formas de executar a aplicação:
- Via Spring Run, executando o seguinte comando Maven:
>**$  mvn spring-boot:run**
- Via JRE, executando o seguinte comando:
>**$  java -jar target/prevsep.web.api.jar**

### Utilizando a aplicação
Uma vez compilada e executada a API PrevSep estará disponível em:
*http://localhost:8080*. A documentação **OAS** pode ser acessada através do *Swagger* da aplicação em *http://localhost:8080/swagger-ui.html*.
### Fazendo o uso  de uma IDE
- IntelliJ
	- Abrir a IDE
	- Selecionar o menu *File -> Open* e escolher o diretório do repositório
	- Abrir o menu *Maven* e executar as *goals*:
		- clean
		- install
	- Executar a classe *br.ufs.hu.prevsep.web.api.PrevSepApplication.java*
	
### Problemas de IDE conhecidos
- IntelliJ
	- Pode ser que classes iniciadas com Q não sejam detectadas após maven clean && maven install.
		
		- Solução: Executar comando "Generate Sources and Update Folders" do Maven e depois clean/install.
	  		Comando pode ser encontrado na janela do Maven ou clicando com o botão direito
	  		no projeto e indo na aba do Maven.
		  
	- Caso falte uma dependência e mesmo após adicioná-la no pom.xml,
	o erro se mantenha.
	  	
		- Solução: Executar comando "Generate Sources and Update Folders" do Maven e depois clean/install.