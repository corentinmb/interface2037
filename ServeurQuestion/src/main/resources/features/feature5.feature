# language: fr

Fonctionnalité: Un système expert ne peut pas répondre à la question
  Scénario: Impossibilité de répondre
    Etant donné que le système expert a récupéré une question en attente
      Et qu'il ne peut pas fournir la réponse
    Quand il notifie le serveur de son échec
    Alors le serveur enregistre que cette question n'a pas de réponse connue.
