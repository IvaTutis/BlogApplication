1. pokrenuti mongo sa `docker start nbp-mongo`
2. kopirati dokumente __test.csv__ i __login.csv__ u npr na linuxima:
 /home/user/
3. kopirati navedene datoteke u kontejner naredbama:

 `docker cp test.csv nbp-mongo:/home/` 
 
 `docker cp login.csv nbp-mongo:/home/`
4. Pokrenuti bash shell naredbom: 

    `docker exec -it nbp-mongo bash`
5. učitati podatke u novu bazu, nbp, kolekcije tekstovi i login:

 `root@43180e0447ea:/# mongoimport -d nbp -c tekstovi --file/home/test.csv`

 `root@43180e0447ea:/# mongoimport -d nbp -c login --file/home/login.csv`
    
*****Napomena:
* Tekstovi su pohranjeni na način da je svaki novi red označen znakom `\n`-> prilikom ispisa potrebno je zamjenit novim redom
* Slike su pohranjene na način da slika sa id-jem 1 (za tekst 1)ima oznaku: `t1s1.png`/`.jpg`/`.jpeg`
* Struktura podataka:

/ link s kojeg kradem podatke: https://markozupanic.hr/blogovi/
// 6 kategorija blogova - biznis & karijera, marketing & SEO, digitalno poslovanje,
// osobni razvoj, trening & prehrana, putovanja

//-------------------------tekstovi----------------------
/*
{
    "text_id" : ,
    "text" : ,
    "naslov" : ,
    "autor" : "korisnicko_ime",
    "teme" : [
        
    ],
    "komentari" : [
        {
            "korisnicko_ime" : ,
            "text" : 
        },
        {
            "korisnicko_ime" : ,
            "text" : 
        }
    ],
    "slike" : [

    ]
}
*/

//-------------------------login(autori)----------------------
/*
{
    "korisnicko_ime" : ,
    "sifra" : 
}
*/
