This is a sample example of a dummy Spring Boot application fetching credentials
from CyberArk using the Credential Providers JavaPasswordSDK

# Pre-Requisits

1. A CyberArk Instance
    A safe, an account, an Application (CyberArk Application ID) with all authorizations  

2. Credential Provider installed on the deployment target
    Provider added to the same Safe as in step 1
    The deployment target will also serve a development env

# Setup the project

1. Install the javapasswordsdk.jar as an artifact with mvn or mvnw:
    `mvn install:install-file -Dfile=/opt/CARKaim/sdk/javapasswordsdk.jar -DgroupId=com.cyberark -DartifactId=javapasswordsdk -Dversion="14.0.1-1" -Dpackaging=jar`
    for the exact version of the jar, extract the MANIFEST.MF in the META-INF directory of the jar file,
    This should have a Implementation-Version key with the right version to apply

2. Configure the application.properties:

    ``` spring.application.name=demo-cybr
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    # For the credential provider the javapasswordsdk key is going to be used as a namespace for the configuration
    # CyberArk AppId
    javapasswordsdk.datasource.appId=springbootdemo
    # CyberArk Safe
    javapasswordsdk.datasource.safe=springbootdemo
    # CyberArk default folder is Root
    javapasswordsdk.datasource.folder=Root
    # CyberArk account (referred to as object)
    javapasswordsdk.datasource.object=Database-PostgreSQL-localhost-pguser

    # Datasource needs to have a separate prefix, here I chose app.*
    # Using the standard spring.datasource in this case won't work.
    app.datasource.url=jdbc:postgresql://localhost:5432/springbootdemo
    app.datasource.configuration.maximum-pool-size=30

3. Run the app