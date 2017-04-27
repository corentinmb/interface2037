# language: fr

Fonctionnalité: Un système expert répond à la question
  Scénario: Enregistrement de la réponse
    Etant donné que le système expert a récupéré une question en attente
      Et qu'il a trouvé une réponse
    Quand il fournit la réponse au serveur
    Alors le serveur indique qu'il a enregistré la réponse à la question
