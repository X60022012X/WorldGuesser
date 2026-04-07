# WorldGuesser

## Beskrivelse av appen

Appen vi har lagd er en geografiquiz, hvor man må trykke på rett land på et kart. Kartet er fra en SVG-fil, og viser Europa. Når en spiller starter appen kan den enten trykke på play eller highscore. Hvis man trykker på highscore får man en liste med de topp fem beste tidene så langt, eventuelt færre dersom det ikke er spilt fem runder enda. Dersom spilleren trykker på play laster spillet inn kartet. Øverst på skjermen står det Find: (country), som viser et tilfeldig land du enda ikke har trykket på. Du har tre forsøk på å trykke på rett land. Dersom du trykker rett land, blir landet grønt, og fjernes fra lista med mulige land du kan måtte finne. Dersom du trykker feil øker antallet bomklikk, og landet du trykket på lyser rødt i 0.5 sekunder. Totaltiden din beregnes ved å ta tid + bomklikk*5. Denne totaltiden blir sendt til en csv-fil, og vil eventuelt vises i highscore dersom den er god nok.

## Diagram
Diagrammet viser strukturen i spilldelen av WorldGuesser. GameController fungerer som bindeledd mellom brukergrensesnittet og modellklassene. Den kommuniserer med WorldGuesser som håndterer spilllogikken, og GameMap som representerer kartet. Country brukes både av GameMap og WorldGuesser for å representere landene i spillet. Timer brukes til å måle tid, som Score og ScoreStorage bruker til beregning og lagring av resultater gjennom GameController.
![Class Diagram](diagram.png)

## Refleksjon

### 1. Dekning av pensum
Prosjektet vårt dekker flere viktige deler av pensum. Først og fremst har vi brukt objektorientert programmering, slik at programmet er delt opp i flere ulike klasser som representerer ulike deler av systemet. Eksempelvis representerer Country-objekter fra Country-klassen ulike land i spillet, mens WorldGuesser driver selve spillogikken. Slik modelleres systemet av objekter som både har tilstand og oppførsel.

Programmet deles opp i ulike ansvarsområder til ulike klasser. For eksempel håndterer GameController brukergrensesnittet, mens Score har ansvar for å beregne resultat for runden basert på tid og bomklikk, og ScoreStorage lagrer de ulike filene i en csv-fil. Da blir koden i sin helhet mer oversiktlig og lett å vedlikeholde, noe som er viktig i objektorientert programmering.

Programmet vårt har også benyttet ArrayLists, blant annet for å finne de beste tidene fra alle rundene som er lagret i csv-filen. Vi har også benyttet JUnit-tester for å teste noen av de viktigste delene av spillogikken, slik som håndtering av feilklikk, redusering av antall forsøk igjen og beregning av score fra runder. Vi utviklet også en grafisk applikasjon i JavaFX for appen vår. Dermed har vi vært innom mange viktige deler av pensum.

### 2. Mulig dekning av resten av pensum
Selv om prosjektet vårt dekker mye av pensum, finnes det fortsatt konsepter som helt klart kunne vært brukt mer. Vår bruk av interfaces var minimal, og ble i stor grad gjort for å oppfylle kravet. Vi kunne for eksempel benyttet interfaces i forbindelse med lagring av scores. Slik programmet er nå, håndteres lagringen i ScoreStorage, men man kunne definert et eget interface for lagring av score. Da kunne flere ulike implementasjoner av lagring vært mulig uten å endre på resten av programmet. 

Et annet konsept vi kunne brukt mer er arv. I prosjektet har vi for det meste brukt samarbeid mellom klasser i stedet for arv. Arv kunne for eksempel vært brukt for å lage en mer generell struktur for ulike typer spill, slik som andre verdensdeler, slik at nye varianter av spillet enklere kunne bli lagt til.

### 3. Model-View-Controller-prinsippet
Vi har forsøkt å følge Model-View-Controller-prinsippet i så stor grad som mulig i prosjektet vårt. Modell-delen består av klasser som håndterer data og logikk i programmet, slik som Country, WorldGuesser, Score, ScoreStorage og Timer. Modell-klassene representerer tilstanden til spillet og funksjonaliteten uten direkte å være tilknyttet brukergrensesnittet.

View-delen består av FXML-filene og de grafiske komponentene i JavaFX som viser kartet, knappene og informasjon til spilleren. Controller-delen består av de klassene som håndterer samhandlingene mellom brukergrensesnittet og modellklassene. Dette gjelder da særlig GameController, MainController og HighscoreController, da de reagerer på input fra brukeren og oppdaterer både modellen og brukergrensesnittet. Selv om strukturen til programmet vårt i stor grad følger MVC-prinsippet, er ikke separasjonen helt perfekt. Country-klassen håndterer for eksempel også hvordan landene vises på kartet, siden landenes farge endres der. Ideelt sett skulle dette vært håndtert et annet sted enn i en modell-klasse.



### 4. Testing av appen
Vi har brukt JUnit-tester for å teste de mest sentrale delene av spillogikken. Aller mest fokuserer testene på logikken i WorldGuesser-klassen, siden dette er den delen av hele programmet hvor det er aller størst sjanse for logiske feil. Blant det som kan gå galt er det at antallet misclicks øker ved feil klikk, at antall gjenværende forsøk reduseres ved feil klikk, at land fjernes fra listen med gjenværende land når det trykkes riktig, og at spillet faktisk avsluttes når denne lista over gjenværende land blir tom. 

Vi testet også beregningen av score etter fullførte forsøk i Score-klassen, for å forsikre oss om at beregningene foretas korrekt. Vi har like fullt overhodet ikke testet hele programmet. Spesielt GUI-delen av appen er vanskelig å teste, siden den er koblet mer opp mot JavaFX og brukergrensesnittet. Vi har derfor prioritert å teste modellklassenes logikk, da de er enkle å teste og det vil ha store konsekvenser dersom de ikke funker.

## KI Deklarasjon
Vi har hovedsakelig brukt KI som et søkeverktøy, som et alternativ til kilder som W3Schools, GeeksforGeeks, Stack Overflow og Reddit. Bruken av KI var i stor grad knyttet til deler av prosjektet som falt utenfor emnets pensum, i dette arbeidet benyttet vi primært språkmodellen ChatGPT. I tillegg brukte vi også Copilot som et hjelpemiddel for å kommentere koden.