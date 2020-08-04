Projet 6 OpenClassrooms | Site d'escalade

Vous pouvez rapidement lancer la webapp via le fichier WAR situé dans le dossier "target".
Assurez-vous d'avoir installé Tomcat sur votre poste et d'avoir mis le service en route. Ajouter le WAR dans le dossier "webapp" de Tomcat.
(Pour que la webapp se lance à la racine de Tomcat, supprimez le ROOT, puis renommez le WAR en ROOT avant de le déplacer dans "webapp")
Dans le dossier "bin" de Tomcat se trouve le fichier "catalina.bat". Lancez-le depuis le CMD via la commande "catalina.bat run" une fois placé dans le répertoire correspondant.

Le site est maintenant accessible via Tomcat.

Pour configurer la base de donnée, il faut accéder au fichier de configuration Spring "application.properties".
Le fichier SQL nommé "Initialize.sql" permet d'insérer des données de test dans la base de données.
