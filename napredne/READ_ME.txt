Za pokretanje aplikacije, potrebno je:

potrebno je skinuti jdk: https://www.oracle.com/java/technologies/javase-downloads.html
potrebno je skinuti jre: https://www.java.com/en/download/
potrebno je skinuti apache maven: https://maven.apache.org/download.cgi
potrebno je skinuti mongodb: https://www.mongodb.com/download-center
potrebno je skinuti apache tomcat: https://tomcat.apache.org/download-80.cgi

za pokretanje aplikacije:
u folderu apache tomcate, unutar poddirektorija webapps staviti ovu datoteku. nakon toga, ukljuciti apache tomcat
naredbom bin/startup.bat (korisnik se mora nalaziti unutar vrsnog direktorija)
aplikacija se pokrece is internet preglednika, pomocu linka: http://localhost:8080/blog/index.jsp
nakon sto je aplikacija zavrsila s radom, isljuciti tomcat pomocu naredbe: bin/shutdown.bat

Nakon toga je potrebno se vratiti u parent file i MongoDB napuniti podacima prema prilozenim podacima

Struktura koda:
Kod je organiziran u 3 funkcionalna dijela. Jedan dio se bavi bazama podataka,
jedan dio priprema podatke koji se trebaju prikazati u aplikaciji ili koje 
treba nekako spremiti u bazu podataka. Sve stranice same aplikacije se nalaze
u posebnoj datoteci. Ostali kod je organiziran u 4 paketa, jedan se bavi samo bazama podatkaa,
drugi drzi objekte baze podataka u obliku kojeg java prepoznaje, treci sluzi samo
za trenutak pokretanja aplikacije, i u cetvrtom paketu su razredi, tj servleti
koji pripremaju podatke za stranice aplikacije. Isto tako, ti servleti mogu komunicirati
sa stranicama i mogu komunicirati s bazom podataka preko objekta koji je posebno napravljen
za komuniciranje s bazom podataka, i koji se naravno nalazi u paketu za baze podataka.