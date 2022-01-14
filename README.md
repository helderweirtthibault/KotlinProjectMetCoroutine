# Project: Netwerk & hardware belasting

De applicatie probeert op een representatieve wijze gegevens te verzamelen in de netwerkcommunicatie.
Er werd gebruikgemaakt van drie verschillende WeatherAPIs en van de Google Maps API omdat de toepassingen populair zijn in de ontwikkeling van applicaties.
In de applicatie zijn vele verschillende werkingen getest of geëxperimenteerd, en is het mogelijk dat sommige code niet relevant meer is voor de huidige werking.
De hardware belasting zal worden gemeten met de profiler tools en de meerwaarde in netwerkcommunicatie via testen.

Uiteindelijk wordt om accurate gegevens de applicatie opgesplist in meerdere applicaties waarbij deze de coroutines bevat. Dit voorkomt dat de data deels beïnvloedt wordt derden.

Deze publieke repository is enkel bruikbaar nadat de API sleutels vervangen worden.

## API KEYS

Voor het vervangen van de sleutels moet telkens in de ViewModel strings een persoonlijk aangemaakte key ingegeven worden.
Maak voor elk van de Weather APIs via de algemene site een account en sleutel aan:

- openweather
- weatherapi

Voor google maps moet deze tutorial gevolgd worden voor het verkrijgen van een sleutel:
https://developers.google.com/maps/documentation/maps-static/get-api-key

## TODO - planning


## Werking huidige applicatie

### WeatherAPIs

De app kan navigeren naar twee verschillende fragmenten die gebruikmaken van een viewmodel. De NetworkCallragment probeert data op te halen uit drie verschillende API calls zonder coroutines en vult de livedata op. 
Het andere fragment heeft dezelfde werking maar maakt gebruik van coroutines. 
Deze API requests werden meerdere malen gestuurd en in verschillende coroutinescopes getest.

De testen berekenen de totale executie tijd van het ophalen van data voor zowel de werkwijze met als zonder coroutine.

### Google Maps API

Wanneer Google Maps wordt geopend in de applicatie, probeert het systeem de locatie van de gebruiker op te halen.
Als het systeem de locatie heeft opgehaald, zal de markeerstip wijzigen.

De visuele UI testen berekenen hoeveel tijd nodig is om Google Maps te openen, de locatie van de gebruiker op te vragen en ten slotte de coördinaten te wijzigen.

