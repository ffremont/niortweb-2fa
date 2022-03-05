# Projet Démo 2FA NiortWeb
Ce projet met en application une authentification 2FA basé sur TOTP.  
Technologies utilisées
- ReactJs
- SpringBoot
- Zxing pour le QR Google Authenticator
- jjwt

## Pré-requis
1. Builder l'application
```shell
./mvnw clean install
```

2. Installer **Google Authenticator**



## Tester
### Démarrer le back
```shell
java -jar target/2fa-0.0.1-SNAPSHOT.jar
```
### Google Authenticator
Scanner le QR sur **[http://localhost:8080/api/users/ffremont/2fa](http://localhost:8080/api/users/ffremont/2fa)**

### Accéder au front
Aller sur **[http://localhost:8080](http://localhost:8080)**

- login: ffremont
- mot de passe : azerty
- 2FA, cf l'application Google Authenticator
