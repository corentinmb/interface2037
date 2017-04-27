# language: fr

Fonctionnalité: Un usager consulte la réponse à sa question
  Scénario: En attente de réponse
    Etant donné qu'un usager a posé une question
      Et aucun système expert n'a encore traité la question
    Quand l'usager demande à consulter la réponse
    Alors le serveur indique que la réponse n'est pas encore disponible

  Scénario: Affichage de la réponse
    Etant donné qu'un usager a posé une question
      Et un système expert a traité la question
    Quand l'usager demande à consulter la réponse
    Alors le serveur affiche la réponse du système expert
