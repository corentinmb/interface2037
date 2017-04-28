# language: fr

Fonctionnalité: Un système expert consulte la prochaine question
  Scénario: Récupérer la prochaine question
    Etant donné qu'il existe une question en attente de réponse
    Quand le système expert demande la prochaine question au serveur
    Alors il récupère la question en attente
      Et la question suivante devient la question en attente

  Scénario: Aucune question en attente
    Etant donné qu'il n'existe aucune question
    Quand le système expert demande la prochaine question au serveur
    Alors le serveur indique qu'il n'existe pas de question
      Et le système expert se met veille avant de redemander une question
