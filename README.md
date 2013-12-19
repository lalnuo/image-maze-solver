![ScreenShot](http://i.imgur.com/UFDvgbm.png)

Labyrinttiratkoja
=================
[![Build Status](https://travis-ci.org/lallinuo/image-maze-solver.png?branch=master)](https://travis-ci.org/lallinuo/image-maze-solver)

Tietorakenteet ja algoritmit harjoitustyö.

Käyttöohje
==
Labyrintin käsittely: 
-

Ohjelma toimii parhaiten .gif ja .png tyyppisillä kuvilla. .jpeg kuvien käyttö “omalla vastuulla”. Merkkaa kuvaan alkupiste punaisella ja päätepiste sinisellä. Värien tulee olla tarkalleen punainen eli RGB 255,0,0 ja tarkalleen sininen 0,0,255. Värien valinta onnistuu helposti esim Paintilla tai GIMPillä. Vältä labyrinttejä joissa seinät ovat vain yhden pikselin paksuisia, sillä myös sivuttain liikkuminen on mahdollista, joten 2 vinottain vierekkäisen pikselin välistä pääsee läpi. 

Ohjelman käyttäminen:
-

Suorita ohjelma tuplaklikkaamalla .jar tiedostoa, tai suorittamalla run.sh. Ohjelma kysyy sinulta nyt labyrinttikuvan sijaintia. Anna labyrintin tiedostonimi. Labyrinttitiedoston tulee olla samass kansiossa kun .jar tiedosto. Seuraavaksi ohjelma kysyy sinulta talletustiedoston nimeä. Anna tiedostonimi, ja lisää .png pääte, esim. “ratkottu.png”. Lopulta valitse haluatko käyttää Dijkstraa vai A* algoritmiä syöttämälllä numero 1 tai 2. Numero 1 vastaa Dijkstraa ja numero 2 vastaa A
staria (=A*). Tämän jälkeen ohjelma ratkaisee labyrintin, ja reitti ilmestyy ratkottu.png tiedostoon.

Run.sh ja .jar tiedosto löytyvät heti image-maze-solver kansion juuresta.

