Projet 6 OpenClassrooms | Site d'escalade

Ceci est un projet SpringBoot qui ne nécessite pas l'installation de Tomcat. Toutefois, vous devez avoir à votre disposition une installation de PostgreSQL. Si ce n'est pas le cas, rendez-vous sur https://www.postgresql.org/download/ pour télécharger PostgreSQL. 

PostgreSQL met à disposition une interface nommé pgAdmin. Vous pouvez y accéder pour rapidement créer un serveur et une base de donnée. Pour cela :
Object -> Create -> Server..., puis Object -> Create -> Database...

Notez que vous n'avez pas besoin de créer les tables pour l'application, cela se fera automatiquement au lancement.

Pour démarrer le projet...

1- Dirigez-vous dans le dossier src/main/resources et accéder au fichier de configuration Spring application.properties. Ce fichier permet de configurer facilement et simplement la base de donnée. Vous y trouverez 3 paramètres pour la configuration : spring.datasource.url, spring.datasource.password et spring.datasource.username.

Pour l'URL, sachez qu'elle se compose de la sorte : jdbc:postgresql://[NomD'HôteDuServeur]:[Port]/[NomDeLaBDD]
Vous pouvez retrouvez et configurez ces valeurs dans l'interface de PostgreSQL. Notez que le nom d'hôte est "localhost" par défaut.

Inscrivez l'URL correspondante dans le champ prévu à cet effet, puis renseignez le nom d'utilisateur et le mot de passe que vous avez fournis lors de la configuration.

Sauvegardez puis fermez le fichier.

2- Ouvrez le CMD, puis placez-vous dans le répertoire du projet avec la commande "cd".

3- Tapez la commande suivante : mvn -q spring-boot:run

Le projet va s'initialiser et sera disponible via votre navigateur.

Vous pouvez récupérer le fichier Initialize.sql pour fournir un jeu de données de test à l'application. 
Pour ce faire, utilisez l'option Query Tool en faisant un clique-droit sur votre base de donnée dans l'interface pgAdmin. Copier-collez les lignes du fichier sql dans l'interface et exécuter la requête. Les données seront alors ajoutées.

Le mot de passe du compte de test "atom" est "12345".
