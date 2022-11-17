# <h1>ADDRESSPROCESSOR</h1>

<p>Aplicação responsável por processar inclusão, alteração, exclusão, publicar os respectivos eventos para o event sourcing e a camada de query
da plataforma.</p>

<h2><p>Case de exemplo do Design Pattern DAT.</p></h2>

<h3><p>Padrão de projeto criado por Carly Oliveira</p></h3>

<h3><p>Stacks:</p></h3>
</br>
Docker
</br>
Java 17
</br>
SpringBoot
</br>
SpringCloud
</br>
AWS - provido via localStack: AWS SQS, AWS DynamoDB
</br>
Lombok
</br>
MapStruct
</br>
Maven
</br>

<h3>Arquitetura da plataforma</h3>

![](ctmait-platform-address.png)

<h4><p> Instruções para executar o projeto</p></h4>
</br>
1 - Abra o cmd, execute ipconfig, copie o endereço ipv4 do adaptador ethernet e 
altere o endereço dos recursos acessados no application.yml
</br>
2 - Na pasta raiz do projeto executar: mvn clean install
</br>
3 - Na pasta recursos-embedded, execute o start-local-stack.sh para subir
a infra provendo os serviços da aws.
</br>
4 - Na pasta raiz do projeto gere a imagem: docker build  -t ctmaitaddressprocessor .
</br>
5 - Crie/execute o container com a imagem do projeto: docker run -e "SPRING_PROFILES_ACTIVE=local" --name ctmait-addressprocessor ctmaitaddressprocessor
</br>
6 - Import a collection no postman e execute os serviços.
</br>
7 - Acessar o banco: http://localhost:8001/
</br>
8 - Acessar todos os serviços provisionados na localstack: https://app.localstack.cloud
</br>
8.1 - Faça o login
</br>
8.2 - Click em "resources"
</br>
8.3 - Vai listar todos os serviços, o que provisionamos foi SQS e DynamoDB
</br>
8.4 - Acesse um dos serviços provisionados e verifique os dados gerados pela aplicação