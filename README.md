# Projet de POOIG

Ce projet consiste à réaliser des prototypes de deux jeux “conceptuellement proches” afin d'exploiter leurs points communs pour réutiliser le maximum d'éléments.


## Langage de programmation

Ce projet a été réalisé en Java. L'utilisation des classes abstraites a été primordiale pour la réutilisation d'éléments communs.

# Dominos-Carrées

![My Image](DC.png)

Assez proche du jeu des dominos basique, mais cette fois ci, ce sont des tuiles avec des faces à trois valeurs. Le jeu commence avec une première tuile sur le plateau, et continue avec les actions des joueurs.

## Règles du jeu

Un sac de tuiles est mis à la disposition des joueurs. Un joueur pioche une tuile dans ce sac lorsque son tour arrive, et il a trois choix :

- Poser la tuile
- Défausser sa tuile
- Abandonner la partie

A noter qu'il y a des conditions lors du placement de la tuile. Comme décrit sur l'image ci-dessus, la tuile doit :

- Etre en contact avec au moins une autre tuile
- Avoir toutes les valeurs de ses faces qui coïncident avec les tuiles en contact

Le jeu s'arrête lorsque :

- Le sac est vide
- L'un des joueurs a atteint le score maximal

# Carcassone

blablablablablalb

# Jouabilité

Il y a une version graphique (swing & awt) disponible pour ces deux jeux. Il suffit de compiler et lancer la classe :

```
src\main\java\gui\Launcher.java
```

Ainsi qu'une version du jeu des Dominos-Carrées jouable sur shell. Il suffit de compiler et lancer la classe :

```
src\main\java\model\DC\JeuDCShell.java
```

# Membres du projet

- YAZICI Servan

- PARIS Albin